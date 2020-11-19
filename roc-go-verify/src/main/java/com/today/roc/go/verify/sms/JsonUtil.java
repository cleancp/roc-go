package com.today.roc.go.verify.sms;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author liuzw
 */
public class JsonUtil {

    private JsonUtil() {
    }

    private static Gson gson;

    private static synchronized Gson newInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static String toJson(Object obj) {
        return newInstance().toJson(obj);
    }

    /**
     * json字符串转换成bean
     *
     * @param json json字符串
     * @param clz  类
     * @return T
     */
    public static <T> T toBean(String json, Class<T> clz) {
        return newInstance().fromJson(json, clz);
    }

    /**
     * json字符串转换成Map
     *
     * @param json json字符串
     * @param clz  类
     * @return Map
     */
    public static <T> Map<String, T> toMap(String json, Class<T> clz) {
        Map<String, JsonObject> map = newInstance()
                .fromJson(json, new TypeToken<Map<String, JsonObject>>() {
                }.getType());
        Map<String, T> result = new HashMap<>(map.size());
        for (Map.Entry<String, JsonObject> entry : map.entrySet()) {
            result.put(entry.getKey(), newInstance().fromJson(entry.getValue(), clz));
        }
        return result;
    }


    /**
     * json字符串转换成List
     *
     * @param json json字符串
     * @param clz  类
     * @return List
     */
    public static <T> List<T> toList(String json, Class<T> clz) {
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        List<T> list = new ArrayList<>();
        for (final JsonElement elem : array) {
            list.add(newInstance().fromJson(elem, clz));
        }
        return list;
    }

}