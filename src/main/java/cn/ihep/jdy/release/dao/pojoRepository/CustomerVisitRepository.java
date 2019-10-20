package cn.ihep.jdy.release.dao.pojoRepository;

import cn.ihep.jdy.release.pojo.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerVisitRepository extends JpaRepository<CustomerVisit,Long> {

    List<CustomerVisit> findByCustomerId(Long customerId);
    List<CustomerVisit> findByCustomerIdOrderByTime(Long customerId);

    @Query("select p from CustomerVisit p where p.companyId = ?1 and p.time > ?2 and p.status<>0")
    List<CustomerVisit> findByCompanyId(Long companyId, java.sql.Timestamp time);

    List<CustomerVisit> findByToUserId(Long toUserId);
    List<CustomerVisit> findCustomerVisitByCustomerIdAndStatusNotOrderByTime(Long CustomerId,Integer status);
}
