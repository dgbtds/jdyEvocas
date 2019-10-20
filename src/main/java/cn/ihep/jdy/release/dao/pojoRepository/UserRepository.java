package cn.ihep.jdy.release.dao.pojoRepository;

import cn.ihep.jdy.release.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByMobileAndStatus(String mobile, Integer status);

    @Query("select p from User p where p.id in ?1")
    List<User> findByIdIn(Long[] idArray);


}
