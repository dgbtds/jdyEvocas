package cn.ihep.jdy.release.dao.pojoRepository;

import cn.ihep.jdy.release.pojo.AiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AiUserRepository extends JpaRepository<AiUser,Long> {

    List<AiUser> findByCompanyId(Long companyId);
    List<AiUser> findAiUsersByCompanyIdOrderByGroupIdDesc(Long companyId);
    List<AiUser> findAiUsersByCompanyIdAndGroupId(Long companyId,int GroupId);
    List<AiUser> findAiUsersByCompanyIdAndGroupIdOrderBySortIndexAsc(Long companyId,int GroupId);
    List<AiUser> findAiUsersByCompanyIdAndGroupIdAndWorkStatusOrderBySortIndexAsc(Long companyId,int GroupId ,int workstatus);
    List<AiUser> findByCompanyIdOrderBySortIndexAsc(Long companyId);
    AiUser findByUserId(Long userId);

    @Modifying
    @Query(value = "select*from accepter", nativeQuery = true)
    List<AiUser> findall();

    @Modifying
    @Query("update AiUser t set t.workStatus = ?2 where t.userId = ?1")
    int updateWorkStatus(Long userId, Integer status);


    @Modifying
    @Query("update AiUser t set t.groupId = ?2 where t.userId = ?1")
    int updateGroupId(Long userId, int GroupId);
    @Query(" select count (t) as num,t.groupId as groupId from AiUser t where t.companyId=?1 group by groupId")
    List<Map<String,Object>>  countByCompanyIdGrAndGroupId(Long companyId);

    @Query("select count (t) as num from AiUser t where t.companyId=?1 group by t.groupId")
    List<Map<String,Object>>   getGroupNumByCompanyId(Long companyId);


    List<AiUser> findAiUsersByCompanyId(Long companyId);
}
