package cn.ihep.jdy.release.dao.pojoRepository;

import cn.ihep.jdy.release.pojo.AiCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AiCustomerRepository extends JpaRepository<AiCustomer,Long> {


    @Query("select p from AiCustomer p where p.id in ?1")
    List<AiCustomer> findByIdIn(Long[] idArray);
    List<AiCustomer> findAiCustomersByCompanyId(Long companyId);

    AiCustomer findByMobile(String mobile);

    @Transactional
    @Modifying
    @Query("update AiCustomer a set a.customerLogo = ?2 where a.id = ?1")
    void updateLogo(Long customerId, String logoPath);

    List<AiCustomer> findAiCustomersByCounselorId(Long userId);
    AiCustomer findAiCustomerByCompanyIdAndMobile(Long companyId,String mobile);
}
