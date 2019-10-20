package cn.ihep.jdy.release.service.user;


import cn.ihep.jdy.release.dao.model.ResultModel;
import cn.ihep.jdy.release.pojo.AiCustomer;

public interface UserService {

    ResultModel setCustomer(AiCustomer aiCustomer, Long aiUserId);

    ResultModel workStatus(Long uid, Integer status);

    ResultModel getWorkStatus(Long uid);

    ResultModel getMyCustomer(Long aiUserId);

    ResultModel getMyCustomerDetail(Long customerId, Long aiUserId);

    ResultModel addCustomerRecord(Long customerId, Long aiUserId,String content);

    ResultModel delCustomerRecord(Long customerId, Long aiUserId, Long orderId);

    ResultModel getVisitReview(Long customerId, Long aiUserId);

    ResultModel getCustomerRecord(Long customerId, Long aiUserId);
}
