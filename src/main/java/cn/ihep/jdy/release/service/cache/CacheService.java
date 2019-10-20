package cn.ihep.jdy.release.service.cache;


import java.util.List;

public interface CacheService {


    void setAccepterGroupByCompanyId(Long companyId);
    List<Long> getAcceptersBycompanyIdAndGroupId(Long companyId, int count);
    void backToWait(Long aiUserId,Long companyId, int groupId);
    void choseOne(Long aiUserId,Long companyId, int groupId);
}
