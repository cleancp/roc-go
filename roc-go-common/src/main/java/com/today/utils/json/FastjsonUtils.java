package com.today.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.today.bo.JsonBO;

import java.util.List;
import java.util.Map;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 14:04*
 * log.info()
 */
public class FastjsonUtils {

    public static void main(String[] args) {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 2);
        //testToAndFromJson(jsonBO);
        //testDateFormat(jsonBO);
        //testGsonWithFastJson(jsonBO);
        testListAndMap(jsonBO);
    }

    public static void testToAndFromJson(JsonBO jsonBO) {
        String jsonString = JSON.toJSONString(jsonBO);
        JsonBO jsonBO1 = JSON.parseObject(jsonString, JsonBO.class);
        System.out.println(jsonString);
    }

    public static void testDateFormat(JsonBO jsonBO) {
        //方式一、序列化时间格式 默认 yyyy-MM-dd HH:mm:ss
        //String jsonString = JSON.toJSONString(jsonBO,SerializerFeature.WriteDateUseDateFormat);
        //方式二、序列化时间格式 自定义时间格式
        //String jsonString2 = JSON.toJSONStringWithDateFormat(jsonBO,"yyyy-MM-dd HH:mm");
        //方式三、JosnField注解 format属性
        String jsonString3 = JSON.toJSONString(jsonBO);
        System.out.println("=");
    }

    /**
     * gson with fastjson  引用检测
     *
     * @param jsonBO
     */
    public static void testGsonWithFastJson(JsonBO jsonBO) {
        String s = JSON.toJSONStringWithDateFormat(jsonBO, "yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
        //数据格式不正确 fastjson序列化过的数据做了特殊处理（处理一个类里不同对象的相同引用数据,"dataMap":{"1":{"$ref":"$.dataList[0]"}}）
        //引用检测的原因，可以关闭 SerializerFeature.DisableCircularReferenceDetect
        Object o = GsonUtils.fromJson(s, JsonBO.class);
        System.out.println(o);
    }


    public static void testListAndMap(JsonBO jsonBO) {
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
//        SerializeConfig serializeConfig = new SerializeConfig();
        SerializerFeature[] features = new SerializerFeature[2];
        features[0] = SerializerFeature.PrettyFormat;
        features[1] = SerializerFeature.WriteDateUseDateFormat;
        String listJson = JSON.toJSONString(dataList, features);
        JSONArray jsonArray = JSON.parseArray(listJson);
        //JsonBO.JsonSubBO subBO = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(0)), JsonBO.JsonSubBO.class);
        //反序列化为指定对象
        //方式一 TypeReference
        List<JsonBO.JsonSubBO> subBOList = JSON.parseObject(listJson, new TypeReference<List<JsonBO.JsonSubBO>>() {
        });

        Map<String, JsonBO.JsonSubBO> dataMap = jsonBO.getDataMap();
        String mapJson = JSON.toJSONString(dataMap, features);
        JSONObject jsonObjectMap = JSON.parseObject(mapJson);
        //JSON.parseObject(JSONObject.toJSONString(jsonObjectMap.get("1")),JsonBO.JsonSubBO.class);
        Map<String, JsonBO.JsonSubBO> subBOMap = JSON.parseObject(mapJson, new TypeReference<Map<String, JsonBO.JsonSubBO>>() {
        });

        System.out.println("");
    }

}
