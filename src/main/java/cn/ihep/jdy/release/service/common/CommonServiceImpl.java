package cn.ihep.jdy.release.service.common;


import cn.ihep.jdy.release.dao.model.ResultModel;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public ResultModel getResult(List<Map> dataArray) {
        ResultModel resultModel = new ResultModel(0);
        Map<String,List<Map>> data = new HashMap<>();
        data.put("list",dataArray);
        resultModel.setData(data);
        return resultModel;
    }

    @Override
    public int getWeekIndex() {
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_WEEK);
        if (currentDay == 1) {
            currentDay = 7;
        } else {
            currentDay -= 1;
        }

        return currentDay;
    }
}
