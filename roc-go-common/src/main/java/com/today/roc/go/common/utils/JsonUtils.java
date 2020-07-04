package com.today.roc.go.common.utils;

import com.google.common.collect.Lists;
import com.today.roc.go.common.bo.JsonBO;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.util.List;
/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 14:03*
 * log.info()
 */
//@Slf4j
public class JsonUtils {

    public static void main(String[] args) {
        String json = "{\"bo\":{\"age\":1,\"dataList\":[{\"id\":111111111111110,\"subage\":0,\"submoney\":10.66,\"subname\":\"\"},{\"id\":111111111111111,\"subage\":1,\"submoney\":11.66,\"subname\":\"壹元\"},{\"id\":111111111111112,\"subage\":2,\"submoney\":12.66,\"subname\":\"贰元\"},{\"id\":111111111111113,\"subage\":3,\"submoney\":13.66,\"subname\":\"叁元\"},{\"id\":111111111111114,\"subage\":4,\"submoney\":14.66,\"subname\":\"肆元\"}],\"id\":1234567890013213,\"money\":12.33,\"name\":\"李四\"},\"age\":1,\"array\":[{\"id\":111111111111110,\"subage\":0,\"submoney\":10.66,\"subname\":\"\"},{\"id\":111111111111111,\"subage\":1,\"submoney\":11.66,\"subname\":\"壹元\"},{\"id\":111111111111112,\"subage\":2,\"submoney\":12.66,\"subname\":\"贰元\"},{\"id\":111111111111113,\"subage\":3,\"submoney\":13.66,\"subname\":\"叁元\"},{\"id\":111111111111114,\"subage\":4,\"submoney\":14.66,\"subname\":\"肆元\"}]}\n";
//        Object bo = JSONObject.toBean(JSONObject.fromObject(JSONObject.fromObject(json).get("bo")), JsonBO.class);
//        System.out.println(bo);
        JsonBO jsonBO = buildObject(JsonBO.class);
        testJSONArray(jsonBO);
    }


    public static JSONObject putMutiValue(Object... args) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < args.length; i++) {
            if (JSONUtils.isObject(args[i])) {
//                 jsonObject.put()
            }
        }
        return jsonObject;
    }


    public static void testJSONArray(JsonBO jsonBO) {
        //将对象转为JSONArray对象
        JSONArray jsonArray = JSONArray.fromObject(jsonBO);
        System.out.println("jsonArray:"+jsonArray);
        JsonBO[] jsonBOS = (JsonBO[]) JSONArray.toArray(jsonArray, JsonBO.class);
        System.out.println(jsonBOS);
        List<JsonBO.JsonSubBO> dataList = jsonBOS[0].getDataList();
        System.out.println("dataList0:"+dataList.get(0));
        JsonBO.JsonSubBO subBO = dataList.get(0);
        System.out.println(subBO);
        JSONArray dataListArr = JSONArray.fromObject(dataList);
        JsonBO.JsonSubBO[] jsonSubBOS = (JsonBO.JsonSubBO[]) JSONArray.toArray(dataListArr, JsonBO.JsonSubBO.class);
        System.out.println("---------------------");
        for (JsonBO.JsonSubBO jsonSubBO : jsonSubBOS) {
            System.out.println(jsonSubBO);
        }
    }

    public static void testJSONObject(JsonBO jsonBO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bo", jsonBO);
        System.out.println(jsonObject.toString());
        Object o = JSONObject.toBean(jsonObject);
        System.out.println(o);

        jsonObject.put("age", jsonBO.getAge());
        jsonObject.put("array", jsonBO.getDataList());
        System.out.println(jsonObject.toString());
    }

    public static <T> T buildObject(Class<T> obj) {
        T t = null;
        try {
            t = obj.newInstance();
            if (t instanceof JsonBO) {
                JsonBO jsonBO = (JsonBO) t;
                jsonBO.setAge(1);
                jsonBO.setId(1234567890013213L);
                jsonBO.setMoney(12.33);
                jsonBO.setName("李四");

                List<JsonBO.JsonSubBO> list = Lists.newArrayList();
                for (int i = 1; i < 5; i++) {
                    JsonBO.JsonSubBO subBO = new JsonBO.JsonSubBO();
                    subBO.setId(111111111111110L + i);
                    subBO.setSubage(i);
                    subBO.setSubmoney(10.66 + i);
                    subBO.setSubname(new ConverToChinesePart(i).convertToChinese());
                    list.add(subBO);
                }
                jsonBO.setDataList(list);
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
