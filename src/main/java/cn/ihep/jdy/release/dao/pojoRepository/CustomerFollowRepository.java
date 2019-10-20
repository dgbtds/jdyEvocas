package cn.ihep.jdy.release.dao.pojoRepository;

import cn.ihep.jdy.release.pojo.CustomerFollowUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CustomerFollowRepository extends JpaRepository<CustomerFollowUp,Long> {

    List<CustomerFollowUp> findByCustomerIdAndStatus(Long customerId, Integer status);

    @Modifying
    @Query("update CustomerFollowUp p set p.status = 0 where p.id=?1")
    int updateStatus(Long followId);
    CustomerFollowUp findCustomerFollowUpByCreateTime(Timestamp timestamp);

}
