package cn.ihep.jdy.release.Util.JsonTool;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONTools {
    /**
     * 功能：将json串转换为一个<String,Object>类型的map,但是只能进行简单的转换，对于包含JSONArray的数据不做特殊处理；
     *
     * */
    public static Map<String, Object> jsontomap(String resultline){
        Map<String, Object> resultmap = new HashMap<String,Object>();

        //将字符串转换为JSON对象
        JSONObject jsonObject = JSONObject.parseObject(resultline);

        //强制转换对象属性，转换为Map类型，适用于没有JSONArray类型的json串
        resultmap = (Map<String,Object>)jsonObject;

//		for(String key : resultmap.keySet()){
//			System.out.println(key + ":" + resultmap.get(key));
//		}

        return resultmap;
    }


    /**
     *功能：将JSONAarry类型的json数据转化为一个list，list中存储的是一系列map<String,Object>容器；
     *
     *
     * */

    public static ArrayList<Map<String,Object>> jsonArraytoMap(JSONArray dataObject){

        ArrayList<Map<String,Object>> datalist = new ArrayList<Map<String,Object>>();

//		List datalist = new ArrayList();

        //循环JSONArray对象数据
        for(Object object : dataObject){

            JSONObject jsonObject = (JSONObject)object;
            HashMap map = new HashMap<String,String>();

            //循环JSONArray中某一json串，并将json转换为map
            //entrySet()方法返回一个set视图（Set<Map.Entry<K,V>>），遍历set视图中的元素；
            for(Map.Entry entry : jsonObject.entrySet()){

//				if(entry.getValue() instanceof JSONArray){
//
//					map.put((String)entry.getKey(),jsonArraytoMap((JSONArray)entry.getValue()));
//				}
//				else{
//
//					map.put((String)entry.getKey(),entry.getValue());
//				}
                map.put((String)entry.getKey(),entry.getValue());
            }

            //将map加入到list中
            datalist.add(map);

        }


//		datalist =(ArrayList<Map<String, String>>) dataObject;

//		for(Map<String,Object> tempMap :datalist ){
//			System.out.println(tempMap);
//		}
//
        return datalist;

    }




}

