package cn.ihep.jdy.release.dao.model;

import java.util.HashMap;
import java.util.Map;

class ResultMessage {

    private static Map<Integer,String> messageMap = new HashMap<>();

    static {
        messageMap.put(0,"success");
        messageMap.put(1000,"服务器处理失败");
        messageMap.put(1001,"权限不足1");
        messageMap.put(1002,"帐号或密码不正确");
        messageMap.put(1003,"参数不正确");
        messageMap.put(1004,"帐号不存在");

    }

    static String getMessage(Integer status) {
        return messageMap.get(status);
    }
}