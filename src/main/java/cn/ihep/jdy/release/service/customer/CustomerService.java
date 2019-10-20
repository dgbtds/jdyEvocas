package cn.ihep.jdy.release.service.customer;



import cn.ihep.jdy.release.dao.model.ResultModel;


public interface CustomerService {

    ResultModel choseone(Long aiUserId,String customerLogo);
    ResultModel firstVisit(String robotId);


    ResultModel uploadLogo(Long customerId, String logoPath);

}
