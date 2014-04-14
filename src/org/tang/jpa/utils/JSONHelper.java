package org.tang.jpa.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
public class JSONHelper {
    /**
      * 将json对象转换为map集合，通过此方法获取存放map集合键的list集合
      * @param obj
      * @return
      */
    public static List<Object> mapKeys(Map<?,?> map){
         List<Object> keysList = new ArrayList<Object>();
         String columnStr="column";
         for(int i=0;i<map.keySet().size();i++){
             keysList.add(columnStr+(i+1));
         }
//         System.out.println(keysList.size());
         return keysList;
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    /**
     * 将传入的json字符串转换为元素为map集合的List集合
     * @param jsonArrStr
     * @return
     */
    public static List<Map<String, Object>> jsonObjList(String jsonArrStr) {
        List<Map<String, Object>> jsonObjList = new ArrayList<Map<String, Object>>();
        List<?> jsonList = jsonToList(jsonArrStr);
        Gson gson = new Gson();
        for (Object object : jsonList) {
            String jsonStr = gson.toJson(object);
            Map<?, ?> json = jsonToMap(jsonStr);
            jsonObjList.add((Map<String, Object>) json);
        }
        return jsonObjList;
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    /**
     * 将传入的json字符串解析为List集合
     * @param jsonStr
     * @return
     */
    public static List<?> jsonToList(String jsonStr) {
        List<?> ObjectList = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<?>>() {}.getType();
        ObjectList = gson.fromJson(jsonStr, type);
        return ObjectList;
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    /**
     * 将传入的json字符串解析为Map集合
     * @param jsonStr
     * @return
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> ObjectMap = null;
        Gson gson = new Gson();
        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<Map<?,?>>() {}.getType();
        ObjectMap = gson.fromJson(jsonStr, type);
        return ObjectMap;
    }
}
