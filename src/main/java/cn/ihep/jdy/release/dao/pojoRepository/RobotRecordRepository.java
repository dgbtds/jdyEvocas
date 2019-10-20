package cn.ihep.jdy.release.dao.pojoRepository;

import cn.ihep.jdy.release.pojo.RobotRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRecordRepository extends JpaRepository<RobotRecord,Long> {

    RobotRecord findBySn(String sn);

}
