package cn.ihep.jdy.release.service.ManagerService;

import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.dao.pojoRepository.AiCustomerRepository;
import cn.ihep.jdy.release.dao.pojoRepository.AiUserRepository;
import cn.ihep.jdy.release.dao.pojoRepository.CustomerVisitRepository;
import cn.ihep.jdy.release.dao.pojoRepository.UserRepository;
import cn.ihep.jdy.release.pojo.AiCustomer;
import cn.ihep.jdy.release.pojo.AiUser;
import cn.ihep.jdy.release.pojo.CustomerVisit;
import cn.ihep.jdy.release.pojo.User;
import cn.ihep.jdy.release.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {

    //    @Autowired
//    private GroupCompanyRepository groupCompanyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AiUserRepository aiUserRepository;
    @Autowired
    private CommonService commonService;
    @Autowired
    private AiCustomerRepository aiCustomerRepository;
    @Autowired
    private CustomerVisitRepository customerVisitRepository;



    @Override
    public ResultModel getCustomerList(Long userId) {
        ResultModel resultModel=new ResultModel(0);
        Map<String,Object>map = new HashMap<String,Object>();

        User manager=userRepository.getOne(userId);
        Long companyId=manager.getCompanyId();
        List<AiCustomer> customers = aiCustomerRepository.findAiCustomersByCompanyId(companyId);
        map.put("cutomerSize",customers.size());
        map.put("customersList",customers);

        resultModel.setData(map);
        return resultModel;
    }

    @Override
    public ResultModel getGroupbyCompany(Long userId) {
        ResultModel resultModel=new ResultModel(0);
        User user = userRepository.getOne(userId);//获取不到自己会报异常处理不用检测了
        Long companyId = user.getCompanyId();
        int groupSize = aiUserRepository.getGroupNumByCompanyId(companyId).size();

        for(int i =0;i<groupSize;i++){
            ResultModel model = getGroupbyCompanyAndGroupId(userId, i);
            resultModel.setData(model.getData());
        }
        return resultModel;
    }

    @Override
    public ResultModel setworkStatus(Long managerId,Long AiuserId, Integer status) {
        getFromParams(managerId,AiuserId);
        aiUserRepository.updateWorkStatus(AiuserId,status);
        return new ResultModel(0);
    }

    @Override
    public ResultModel getGroupbyCompanyAndGroupId(Long userId, int groupid) {
        ResultModel resultModel=new ResultModel(0);
        Map<String,Object> map=new HashMap<>();


        User user = userRepository.getOne(userId);//获取不到自己会报异常处理不用检测了
        Long companyId = user.getCompanyId();

        int groupSize = aiUserRepository.getGroupNumByCompanyId(companyId).size();
        if(groupid>groupSize) {
            return new ResultModel(1003,"无此组号");
        }
        List<AiUser> list = aiUserRepository.findAiUsersByCompanyIdAndGroupIdOrderBySortIndexAsc(companyId,groupid);
        map.put("Group_"+groupid+"_list",list);
        resultModel.setData(map);

        return resultModel;
    }
    @Transactional
    @Override
    public ResultModel transfer(Long managerId, Long fromAiuserId, Long toAiuserId) {
        User manager=userRepository.getOne(managerId);
        AiUser fromAiUser = aiUserRepository.findByUserId(fromAiuserId);
        AiUser toAIUser = aiUserRepository.findByUserId(toAiuserId);
        if(!(
                manager.getCompanyId().longValue()== fromAiUser.getCompanyId().longValue()&&
                        fromAiUser.getCompanyId().longValue()==toAIUser.getCompanyId().longValue()
        )
        ){
            return new ResultModel(1003,"非一家公司人员");
        }
        List<AiCustomer> Customers = aiCustomerRepository.findAiCustomersByCounselorId(fromAiuserId);
        Customers.forEach(
                c->{c.setCounselorId(toAiuserId);
                    aiCustomerRepository.save(c);
                    //到访记录表
                    CustomerVisit customerVisit=new CustomerVisit();
                    customerVisit.setCompanyId(fromAiUser.getCompanyId());
                    customerVisit.setWeekIndex(commonService.getWeekIndex());
                    customerVisit.setTime(new Timestamp(System.currentTimeMillis()));
                    customerVisit.setCustomerId(c.getId());
                    customerVisit.setToUserId(toAiuserId);
                    customerVisit.setFromUserId(fromAiuserId);
                    customerVisit.setStatus(0);
                    customerVisitRepository.save(customerVisit);
                });


        ResultModel resultModel=new ResultModel(0);
        Map<String,Object> map=new HashMap<>();
        map.put("toAiuserId",toAiuserId);
        map.put("fromAiuserId",fromAiuserId);
        map.put("managerId",managerId);
        map.put("passCustomerCount",Customers.size());

        resultModel.setData(map);

        return resultModel;
    }

    @Transactional
    @Override
    public ResultModel deleteGroup(Long userId,int groupid) {
        User user = userRepository.getOne(userId);//findone返回optinal类型，.get（）得到本类型
        Long companyId = user.getCompanyId();
        List<AiUser> aiUsers = aiUserRepository.findAiUsersByCompanyId(companyId);
        //耗费时间：普通for循环<并行流分组<串行流分组
        Map<Integer, List<AiUser>> collect = aiUsers.parallelStream().collect(
                Collectors.groupingBy(AiUser::getGroupId
                        //    ,Collectors.groupingBy(AiUser::getUserId)//还可以继续切分
                ));
        int size = collect.size();//有人的组的数量

        collect.forEach((k,v)->{
            if(k==groupid){
                v.forEach(c->{
                    c.setGroupId(0);
                    aiUserRepository.save(c);
                } );
            }

        });
        ResultModel resultModel=new ResultModel(0);
        return resultModel;
    }
    @Transactional
    @Override
    public ResultModel changeGroup( Long userId,Long AiuserId, int groupid) {

        Map<String,Object> mapclass= (Map<String, Object>) getFromParams(userId,AiuserId).getData();
        AiUser aiUser= (AiUser) mapclass.get("aiuser");


        aiUser.setGroupId(groupid);
        aiUserRepository.save(aiUser);

        ResultModel resultModel=new ResultModel(0);
//        Map<String,Object> map=new HashMap<>();
//        map.put("userId",userId);
//        map.put("AiuserId",AiuserId);
//        map.put("companyId",companyId);
//        map.put("fromgroup",fromgroup);
//        map.put("togroup",groupid);
//        resultModel.setData(map);

        return resultModel;
    }

    @Override
    public ResultModel changeGrouplist(Long userId,Long[] AiuserIds, int groupid) {
        for(Long a:AiuserIds){
            ResultModel Model=changeGroup(userId,a,groupid);
        }
        return  new ResultModel(0);
    }
    @Transactional
    @Override
    public ResultModel setIndex(Long userId, Long AiuserId, Long index) {
        Map<String,Object> mapclass= (Map<String, Object>) getFromParams(userId,AiuserId).getData();
        AiUser aiUser= (AiUser) mapclass.get("aiuser");
        User user= (User) mapclass.get("user");


        aiUser.setSortIndex(index);
        aiUserRepository.save(aiUser);

        ResultModel resultModel=new ResultModel(0);

        return resultModel;
    }
    @Override
    public ResultModel setIndexlist(Long userId, Long[] AiuserIds, Long[] indexs) {

        if(AiuserIds.length!=indexs.length){
            return new ResultModel(1003);
        }
        for(int i=0;i<indexs.length;i++){

            setIndex(userId,AiuserIds[i],indexs[i]);
        }
        return new ResultModel(0);
    }

    @Override
    public ResultModel getPassHistory(Long userId) {
        ResultModel resultModel=new ResultModel(0);
        Map<String,Object>map = new HashMap<String,Object>();

        User manager=userRepository.getOne(userId);
        Long companyId=manager.getCompanyId();
        List<CustomerVisit> customerPass = customerVisitRepository.findCustomerVisitByCustomerIdAndStatusNotOrderByTime(companyId, 3);
        resultModel.setData(customerPass);
        return resultModel;
    }

    @Transactional
    @Override
    public ResultModel setTimes(Long userId, Long AiuserId, Long times) {
        Map<String,Object>mapclass= (Map<String, Object>) getFromParams(userId,AiuserId).getData();
        AiUser aiUser= (AiUser) mapclass.get("aiuser");
        User user= (User) mapclass.get("user");
        Long companyId= (Long) mapclass.get("companyId");
        Long oldtimes = aiUser.getAcceptTimes();

        if(times.longValue()<0){
            return new ResultModel(1003);
        }
        aiUser.setAcceptTimes(times);
        aiUserRepository.save(aiUser);
        ResultModel resultModel=new ResultModel(0);
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("AiuserId",AiuserId);
        map.put("companyId",companyId);
        map.put("oldtimes",oldtimes);
        map.put("newtimes",times);
        resultModel.setData(map);

        return resultModel;
    }

    @Override
    public ResultModel setTimesList(Long userId, Long[] AiuserIds, Long[] timesList) {
        if(AiuserIds.length!=timesList.length){
            return new ResultModel(1003);
        }
        return new ResultModel(0);
    }



    public ResultModel getFromParams(Long userId, Long AiuserId){
        User user = userRepository.getOne(userId);
        AiUser aiUser = aiUserRepository.findByUserId(AiuserId);
        if(user==null||aiUser==null){
            return new ResultModel(1003);
        }
        Long companyId=user.getCompanyId();
        ResultModel resultModel=new ResultModel(0);
        if(aiUser.getCompanyId().longValue()!=user.getCompanyId().longValue()){
            return new ResultModel(1003,"公司不匹配");
        }
        Map<String,Object> map=new HashMap<>();
        map.put("user",user);
        map.put("aiuser",aiUser);
        map.put("companyId",companyId);
        resultModel.setData(map);
        return resultModel;
    }
}
/*共有代码
        Map<String,Object> mapclass= (Map<String, Object>) getFromParams(userId,AiuserId).getData();
        User user= (User) mapclass.get("user");
        AiUser aiUser= (AiUser) mapclass.get("aiuser");
        Long companyId= (Long) mapclass.get("companyId");
 */