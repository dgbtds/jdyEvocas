package cn.ihep.jdy.release.service.common;


import cn.ihep.jdy.release.dao.model.ResultModel;

import java.util.List;
import java.util.Map;

public interface CommonService {

    ResultModel getResult(List<Map> dataArray);

    int getWeekIndex();
}
