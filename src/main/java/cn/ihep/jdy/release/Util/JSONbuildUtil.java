package cn.ihep.jdy.release.Util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONbuildUtil {
    public static String toJsonString(){
        List<Long> aiusers=new ArrayList<Long>();
        aiusers.add(7L);
        aiusers.add(8L);
        aiusers.add(9L);
        List<Long> timesList=new ArrayList<Long>();
        timesList.add(12L);
        timesList.add(32L);
        timesList.add(45L);
        Map<String,Object> map = new HashMap<String,Object>();

        // map.put("index",4L);
        // map.put("GroupId",3L);
        //  map.put("userIds",[7,8,9]);
         // map.put("customerId",timesList);
          map.put("robotId",3296);
       // map.put("indexlist",timesList);
       // map.put("times",4L);
        map.put("userId",7L);

       // map.put("AiuserId",7L);

        String str2 = JSON.toJSONString(map);

        System.out.println("str2="+str2);
        return  str2;
    }
}
