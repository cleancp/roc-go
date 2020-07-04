package com.today.roc.go.common.utils.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.today.roc.go.common.bo.JsonBO;
import com.today.roc.go.common.bo.Person;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 14:23*
 * log.info()
 */
public class JacksonUtils {

    public static void main(String[] args) throws IOException {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 1);
        String json = toJson(jsonBO);
        testListAndMap();
    }

    //处理List 和 MAP
    public static void testListAndMap() throws IOException {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 1);
        JsonBO o = (JsonBO)fromJson(toJson(jsonBO), new Class[]{JsonBO.class});
        System.out.println(o);
        //list
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
        String jsonList = toJson(dataList);
        List<JsonBO.JsonSubBO> subBOList = (List<JsonBO.JsonSubBO>) fromJson(jsonList, new Class[]{List.class, JsonBO.JsonSubBO.class});
        //map
        Map<String, JsonBO.JsonSubBO> dataMap = jsonBO.getDataMap();
        String jsonMap = toJson(dataMap);
        Map<String, JsonBO.JsonSubBO> subBOMap = (Map<String, JsonBO.JsonSubBO>) fromJson(jsonMap, new Class[]{Map.class, String.class, JsonBO.JsonSubBO.class});
        System.out.println(subBOList);
    }

    public static Object fromJson(String json, Class[] clazzs) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj;
        if (clazzs.length == 2) {
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(clazzs[0], clazzs[1]);
            obj = mapper.readValue(json, listType);
            //除了默认Long型时间戳 与gson兼容
//            List<JsonBO.JsonSubBO> jsonSubBOList = (List<JsonBO.JsonSubBO>) GsonUtils.fromJson(json,
//                    new TypeToken<List<JsonBO.JsonSubBO>>() {
//                    }.getType());

        } else if (clazzs.length == 3) {
            MapType mapType = mapper.getTypeFactory().constructMapType(clazzs[0], clazzs[1], clazzs[2]);
            obj = mapper.readValue(json, mapType);
            //除了默认Long型时间戳 与gson兼容
//            Map<String, JsonBO.JsonSubBO> jsonSubBOMap = (Map<String, JsonBO.JsonSubBO>) GsonUtils.fromJson(jsonMap,
//                    new TypeToken<Map<String, JsonBO.JsonSubBO>>() {
//                    }.getType());
        } else {
            obj = mapper.readValue(json, clazzs[0]);
        }
        return obj;
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ  如果有注解 优先注解
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //在序列化时忽略值为 null 的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略值为默认值的属性
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        return json;
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //在反序列化时忽略在 JSON 中存在但 Java 对象不存在的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T t = mapper.readValue(json, clazz);
        return t;
    }


}
