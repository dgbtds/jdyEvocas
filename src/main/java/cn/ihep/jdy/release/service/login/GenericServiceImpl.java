package cn.ihep.jdy.release.service.login;

import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.dao.model.UserPrincipal;
import cn.ihep.jdy.release.dao.pojoRepository.CustomerVisitRepository;
import cn.ihep.jdy.release.dao.pojoRepository.UserRepository;
import cn.ihep.jdy.release.pojo.CustomerVisit;
import cn.ihep.jdy.release.pojo.User;
import cn.ihep.jdy.release.service.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerVisitRepository customerVisitRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;


    @Override
    public ResultModel login(String mobile,String pass) {
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(mobile, pass);
        final Authentication authentication = authenticationManager.authenticate(userToken);//到JwtUserDetailsServiceImpl.loadUserByUsername
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal)authentication.getPrincipal();
        String token = jwtToken.generateToken(principal);
        Map<String,String> resultData = new HashMap<>();
        User user = userRepository.findByMobileAndStatus(mobile,1);

        resultData.put("token",token);
        resultData.put("userId",user.getId().toString());
        resultData.put("roleId",user.getRoleId().toString());

        ResultModel result = new ResultModel(0);
        result.setData(resultData);
        return result;
    }

    @Override
    public ResultModel getWeek(Long userId) {
        User user = userRepository.getOne(userId);
        List<Integer> dataArray = Arrays.asList(0,0,0,0,0,0,0);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,-7);
        Timestamp startTime = new Timestamp(cal.getTimeInMillis());
        List<CustomerVisit> visitList = customerVisitRepository.findByCompanyId(user.getCompanyId(),startTime);
        int count = 0;
        for (CustomerVisit customerVisit : visitList) {
            switch (customerVisit.getWeekIndex()) {
                case 1:
                    count = dataArray.get(0);
                    count++;
                    dataArray.set(0,count);
                    break;
                case 2:
                    count = dataArray.get(1);
                    count++;
                    dataArray.set(1,count);
                    break;
                case 3:
                    count = dataArray.get(2);
                    count++;
                    dataArray.set(2,count);
                    break;
                case 4:
                    count = dataArray.get(3);
                    count++;
                    dataArray.set(3,count);
                    break;
                case 5:
                    count = dataArray.get(4);
                    count++;
                    dataArray.set(4,count);
                    break;
                case 6:
                    count = dataArray.get(5);
                    count++;
                    dataArray.set(5,count);
                    break;
                case 7:
                    count = dataArray.get(6);
                    count++;
                    dataArray.set(6,count);
                    break;
                default:
                    break;
            }
        }
        ResultModel resultModel = new ResultModel(0);
        Map<String, List<Integer>> data = new HashMap<>();
        data.put("list",dataArray);
        resultModel.setData(data);
        return resultModel;
    }

}
