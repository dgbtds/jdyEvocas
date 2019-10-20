package cn.ihep.jdy.release.service.customer;

import cn.ihep.jdy.release.Util.JPush.JPushConfig;
import cn.ihep.jdy.release.Util.NonUtil;
import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.dao.pojoRepository.*;
import cn.ihep.jdy.release.pojo.AiUser;
import cn.ihep.jdy.release.pojo.RobotRecord;
import cn.ihep.jdy.release.pojo.User;
import cn.ihep.jdy.release.service.cache.CacheService;
import cn.ihep.jdy.release.service.common.CommonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private AiCustomerRepository aiCustomerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerFollowRepository customerFollowRepository;

    @Autowired
    private CustomerVisitRepository customerVisitRepository;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AiUserRepository aiUserRepository;

    @Autowired
    private JPushConfig jPushConfig;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RobotRecordRepository robotRecordRepository;

//        messageMap.put(0,"success");
//        messageMap.put(1000,"服务器处理失败");
//        messageMap.put(1001,"权限不足");
//        messageMap.put(1002,"帐号或密码不正确");
//        messageMap.put(1003,"参数不正确");
//        messageMap.put(1004,"帐号不存在");



    @Override
    public ResultModel firstVisit(String robotId) {
        if(NonUtil.isNon(robotId)){
            return new ResultModel(1003);
        }
        ResultModel resultModel = new ResultModel(0);

        //1.通过sn获取机器人,通过robotrecord获取userId
        RobotRecord robotRecord = robotRecordRepository.findBySn(robotId);
        if(NonUtil.isNon(robotRecord)) return new ResultModel(1,"所传机器人sn有误");
        Long companyId = robotRecord.getCompanyId();


        User manager = userRepository.getOne(robotRecord.getUserId());
        Long managerId=manager.getId();


        int groupnum=aiUserRepository.getGroupNumByCompanyId(companyId).size()-1;
            if(groupnum<=0){
                return new ResultModel(4,"没有工作组");
            }
        List<AiUser> recommendlist=new ArrayList<>();
        List<Long> recommendId;
        do {
            recommendId=cacheService.getAcceptersBycompanyIdAndGroupId(companyId,3);
            }while (recommendId.size()==0);
        for (Long aiUserId:recommendId){
            AiUser aiUser = aiUserRepository.findByUserId(aiUserId);
            Optional<User> user = userRepository.findById(aiUserId);
            aiUser.setName(user.get().getName());
            recommendlist.add(aiUser);
        }

        Map<String,Object> map=new HashMap<>();
        map.put("recommendlist",recommendlist);
        map.put("managerId",managerId);

        resultModel.setData(map);
      //  simpMessagingTemplate.convertAndSend("/customer/"+robotId,map);//发送给机器人端口

        return resultModel;
    }

    public ResultModel choseone(Long aiUserId,String customerLogo){
    if(NonUtil.isNon(aiUserId,customerLogo)){
        return new ResultModel(1003);
    }
   AiUser aiUser = aiUserRepository.findByUserId(aiUserId);

    ResultModel resultModel = new ResultModel(0);
    Map<String,Object> map1 = new HashMap<>();
    map1.put("customerLogo",customerLogo);
    //发送到手机,信息
    sendMessage(JSONObject.toJSONString(map1),aiUserId.toString());
    cacheService.choseOne(aiUserId,aiUser.getCompanyId(),aiUser.getGroupId());

//    Map<String,Object> map2=new HashMap<>();
//    resultModel.setData(map2);

    return resultModel;
}


    public void sendMessage(String content,String liase) {

        jPushConfig.send(content,liase);

    }

    @Transactional
    @Override
    public ResultModel uploadLogo(Long customerId, String logoPath) {
        ResultModel resultModel = new ResultModel(0);
        aiCustomerRepository.updateLogo(customerId,logoPath);
        return resultModel;
    }


}
