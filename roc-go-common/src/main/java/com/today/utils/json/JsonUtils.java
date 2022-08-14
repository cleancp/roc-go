package com.today.utils.json;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.today.bo.JsonBO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 14:03*
 * System.out.println()
 */
public class JsonUtils {

    public static final String json = "{\"bo\":{\"age\":1,\"dataList\":[{\"id\":111111111111110,\"subage\":0,\"submoney\":10.66,\"subname\":\"\"},{\"id\":111111111111111,\"subage\":1,\"submoney\":11.66,\"subname\":\"壹元\"},{\"id\":111111111111112,\"subage\":2,\"submoney\":12.66,\"subname\":\"贰元\"},{\"id\":111111111111113,\"subage\":3,\"submoney\":13.66,\"subname\":\"叁元\"},{\"id\":111111111111114,\"subage\":4,\"submoney\":14.66,\"subname\":\"肆元\"}],\"id\":1234567890013213,\"money\":12.33,\"name\":\"李四\"},\"age\":1,\"array\":[{\"id\":111111111111110,\"subage\":0,\"submoney\":10.66,\"subname\":\"\"},{\"id\":111111111111111,\"subage\":1,\"submoney\":11.66,\"subname\":\"壹元\"},{\"id\":111111111111112,\"subage\":2,\"submoney\":12.66,\"subname\":\"贰元\"},{\"id\":111111111111113,\"subage\":3,\"submoney\":13.66,\"subname\":\"叁元\"},{\"id\":111111111111114,\"subage\":4,\"submoney\":14.66,\"subname\":\"肆元\"}]}\n";

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        JsonBO jsonBO = buildObject(JsonBO.class,1);
        long middle = System.currentTimeMillis();
        System.out.println("构造数据花费时间：" + (middle - start));
        //testJSONObject(jsonBO);
        testJSONArray(jsonBO);
        long end = System.currentTimeMillis();
        System.out.println("处理数据花费时间：" + (end - middle));
    }

    public static void testJSONArray(JsonBO jsonBO) {
        //将对象转为JSONArray对象
        JSONArray jsonArray = JSONArray.fromObject(jsonBO);
        System.out.println(((JSONObject) jsonArray.get(0)).get("birthday"));
        //jsonArray实现List
        JsonBO[] jsonBOS = (JsonBO[]) JSONArray.toArray(jsonArray, JsonBO.class);
        for (JsonBO bo : jsonBOS) {
            if (bo.getDataList() instanceof List) {
                JSONArray dataListArr = JSONArray.fromObject(bo.getDataList());
                JsonBO.JsonSubBO[] jsonSubBOS = (JsonBO.JsonSubBO[]) JSONArray.toArray(dataListArr, JsonBO.JsonSubBO.class);
                List<JsonBO.JsonSubBO> subBOList = Lists.newArrayList();
                for (JsonBO.JsonSubBO jsonSubBO : jsonSubBOS) {
                    subBOList.add(jsonSubBO);
                }
                bo.setDataList(subBOList);
            }
            if (bo.getDataMap() instanceof Map) {
                Map<String, JsonBO.JsonSubBO> subBOMap = Maps.newHashMap();
                bo.getDataMap().keySet().forEach(
                        k -> {
                            Object v = bo.getDataMap().get(k);
//                            //jsonArray方式  处理数据花费时间：3260
//                            JsonBO.JsonSubBO[] jsonSubBOS = (JsonBO.JsonSubBO[]) JSONArray.toArray(JSONArray.fromObject(v), JsonBO.JsonSubBO.class);
//                            for (JsonBO.JsonSubBO jsonSubBO : jsonSubBOS) {
//                                subBOMap.put(k, jsonSubBO);
//                            }
                            //jsonObject方式1  处理数据花费时间：3143
                            JsonBO.JsonSubBO jsonSubBO = (JsonBO.JsonSubBO) JSONObject.toBean(JSONObject.fromObject(v), JsonBO.JsonSubBO.class);
                            subBOMap.put(k, jsonSubBO);
                        }
                );
                bo.setDataMap(subBOMap);
            }
            System.out.println(bo);
        }
    }

    public static void testJSONObject(JsonBO jsonBO) {
        Set set = JSONObject.fromObject(json).keySet();
        set.forEach(
                k -> {
                    Object v = JSONObject.fromObject(json).get(k);
                    System.out.println("key：" + k);
                    if (v instanceof JSONArray) {
                        JsonBO.JsonSubBO[] jsonSubBOS = (JsonBO.JsonSubBO[]) JSONArray.toArray(JSONArray.fromObject(v), JsonBO.JsonSubBO.class);
                        for (JsonBO.JsonSubBO bo : jsonSubBOS) {
                            System.out.println(bo.toString());
                        }
                    } else if (v instanceof JSONObject) {
                        JsonBO jsonBO1 = (JsonBO) JSONObject.toBean(JSONObject.fromObject(v), JsonBO.class);
                        System.out.println(jsonBO1.toString());
                    } else {
                        System.out.println(v.toString());
                    }
                }
        );
    }

    public static <T> T buildObject(Class<T> obj,int size) {
        T t = null;
        try {
            t = obj.newInstance();
            if (t instanceof JsonBO) {
                JsonBO jsonBO = (JsonBO) t;
                jsonBO.setAge(1);
                jsonBO.setId(1234567890013213L);
                jsonBO.setMoney(12.33);
                jsonBO.setName("李四");
                jsonBO.setBirthday(new Date());

                List<JsonBO.JsonSubBO> list = Lists.newArrayList();
                Map<String, JsonBO.JsonSubBO> map = Maps.newHashMap();
                for (int i = 1; i <= size; i++) {
                    JsonBO.JsonSubBO subBO = new JsonBO.JsonSubBO();
                    subBO.setId(111111111111110L + i);
                    subBO.setSubage(i);
                    subBO.setSubmoney(10.66 + i);
                    subBO.setSubname(i + "");
                    subBO.setSubbirthday(new Date());
                    list.add(subBO);
                    map.put(i + "", subBO);
                }
                jsonBO.setDataList(list);
                jsonBO.setDataMap(map);
                return (T) jsonBO;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
