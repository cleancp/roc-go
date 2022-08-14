package com.today.utils.json;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.today.bo.JsonBO;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
public class GsonUtils {

    public static void main(String[] args) {
        JsonBO jsonBO = JsonUtils.buildObject(JsonBO.class, 1);
        jsonBO.setAge(null);
        jsonBO.setDataList(null);
        //Gson用法
        GsonBuilder gsonBuilder = new GsonBuilder()
                //时间格式化 序列化 反序列化都生效
                .setDateFormat("yyyy-MM-dd")
                //序列化null
                .serializeNulls()
                //格式化输出。设置后，gson序列号后的字符串为一个格式化的字符串
                .setPrettyPrinting()
                //使用@Expose注解的才会暴露
                .excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        //测试策略
        testStrategy(gsonBuilder, jsonBO);
        // @Expose：与GsonBuilder的excludeFieldsWithoutExposeAnnotation一起使用，加注解暴露，否则不暴露
        // @SerializedName： 属性value 可接受的输入输出字段名称 ， 属性alternate定义接受输入的字段名称
        //序列化
        String toJson = gson.toJson(jsonBO);
        //反序列化
        JsonBO fromJson = gson.fromJson(toJson, JsonBO.class);
        //反序列化变形
        String toJosn2 = "{\"xid\":123,\"xage\":13,\"yname\":\"小王\"}";
        JsonBO fromJosn2 = gson.fromJson(toJosn2, JsonBO.class);
        System.out.println(fromJosn2);
        //测试LIST MAP的转换
        testListAndMap(jsonBO, gson);
    }

    public static void testListAndMap(JsonBO jsonBO, Gson gson) {
        List<JsonBO.JsonSubBO> dataList = jsonBO.getDataList();
        String dataListJson = gson.toJson(dataList);
        //泛型擦除 不使用,不满足条件
        //List<JsonBO.JsonSubBO> list1 = (List<JsonBO.JsonSubBO>)gson.fromJson(dataListJson, List.class);
        List<JsonBO.JsonSubBO> list = gson.fromJson(dataListJson, new TypeToken<List<JsonBO.JsonSubBO>>() {
        }.getType());

        Map<String, JsonBO.JsonSubBO> dataMap = jsonBO.getDataMap();
        String dataMapJson = gson.toJson(dataMap);
        //泛型擦除 不使用,不满足条件
        //Map<String, JsonBO.JsonSubBO> map1 = (Map<String, JsonBO.JsonSubBO>)gson.fromJson(dataMapJson, Map.class);
        Map<String, JsonBO.JsonSubBO> map = gson.fromJson(dataMapJson, new TypeToken<Map<String, JsonBO.JsonSubBO>>() {
        }.getType());
        System.out.println(list);
    }

    public static Object fromJson(String json, Type type) {
        Gson gson = new Gson();
        Object o = gson.fromJson(json, type);
        return o;
    }


    public static void testStrategy(GsonBuilder gsonBuilder, JsonBO jsonBO) {
        Gson gson = gsonBuilder.addSerializationExclusionStrategy(serializeStrategy())
                .addDeserializationExclusionStrategy(deserializeStrategy())
                .create();
        //序列化
        String json = gson.toJson(jsonBO);
        System.out.println(json);
        //反序列化
        JsonBO jsonBO1 = gson.fromJson(json, JsonBO.class);
        System.out.println(jsonBO1);
    }

    public static ExclusionStrategy serializeStrategy() {
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                //通过字段名
                if ("age".equals(f.getName())) {
                    return true;
                }
                //通过注解
                Expose expose = f.getAnnotation(Expose.class);
                if (Objects.isNull(expose) || !expose.serialize()) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        };
    }

    public static ExclusionStrategy deserializeStrategy() {
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                //通过字段名
                if ("dataList".equals(f.getName())) {
                    return true;
                }
                //通过注解
                Expose expose = f.getAnnotation(Expose.class);
                if (Objects.isNull(expose) || !expose.deserialize()) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        };
    }

}
