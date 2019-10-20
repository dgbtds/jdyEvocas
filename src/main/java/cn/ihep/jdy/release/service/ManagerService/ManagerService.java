package cn.ihep.jdy.release.service.ManagerService;

import cn.ihep.jdy.release.dao.model.ResultModel;

public interface ManagerService {
    ResultModel getCustomerList(Long userId);
    ResultModel getGroupbyCompany(Long userId);
    ResultModel setworkStatus(Long managerId,Long AiuserId, Integer status);
    ResultModel getGroupbyCompanyAndGroupId(Long userId,int groupid);
    ResultModel deleteGroup(Long userId,int groupid);
    ResultModel changeGroup(Long userId,Long AiuserId,int groupid);
    ResultModel changeGrouplist(Long userId,Long[] AiuserIds, int groupid);
    ResultModel setIndex(Long userId,Long AiuserId,Long index);
    ResultModel setTimes(Long userId, Long AiuserId, Long times);
    ResultModel setTimesList(Long userId, Long[] AiuserIds, Long[] timesList);
    ResultModel setIndexlist(Long userId, Long[] AiuserIdlist, Long[] indexList);
    ResultModel getPassHistory(Long managerId);
    ResultModel transfer(Long managerId,Long fromAiuserId,Long toAiuserId);
}
