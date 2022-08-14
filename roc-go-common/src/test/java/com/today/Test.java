package com.today;

import com.today.bo.Person;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年07月02日 17:41*
 * log.info()
 */
public class Test {


    public static void main(String[] args) {
        /** * 生成JSON字符串 *  */
        Person person = new Person(1, "haungjundong", "beijing");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hjd", person);
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");
        //基本数据类型
        jsonObject.put("int", 123);
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");
        jsonObject.put("string", "hello world");
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");
        //数组
        int arr[] = new int[10];
        for (int i = 0; i < 10; ++i) {
            arr[i] = i;
        }
        jsonObject.put("arr", arr);
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");/** * 生成集合类型数据(1) * 为什么不使用List<基本类型>来距离呢??? * 因为"基本类型的集合"已经有一种形式来表示了:-------->数组 * 如果一定要的话,轻易List<Integer>,然后做一个demo */
        List<String> strList = new ArrayList<String>();
        for (int i = 0; i < 10; ++i) {
            strList.add("str" + i);
        }
        jsonObject.put("strList", strList);
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");
        //生成集合类型数据(2)
        List<Person> personList = new ArrayList<Person>();
        for (int i = 0; i < 10; ++i) {
            personList.add(new Person(i, "hjd" + i, "beijing" + i));
        }
        jsonObject.put("personList", personList);
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");/** * 生成集合类型数据(3) *  * 1) * Caused by: java.lang.ClassCastException:  * JSON keys must be strings.------->JSON对象中的key必须是一个String类型的(字符串) *  * 2)对于下面，可以这样理解(可能有一点不太准确,但是方便理解) * map是一个对象,key为其内部成员,value是key的值 */
        List<Map> mapList = new ArrayList<Map>();
        for (int i = 0; i < 10; ++i) {
            Map map = new HashMap<String, Integer>();
            map.put("" + i, 10 + i);
            mapList.add(map);
        }
        jsonObject.put("mapList", mapList);
        System.out.println("--------------");
        System.out.println(jsonObject.toString());
        System.out.println("--------------");
        System.out.println("------------------->解析json字符串");
        String jsonString = jsonObject.toString();
        //将jsonObject转换成json字符串
        JSONObject jsonObject2 = JSONObject.fromObject(jsonString);
        //利用json字符串生成json对象解析Person对象
        Person person2 = new Person();
        //*****这种写法是错的, 原生的json的解析方法并不支持将json字符串自动转成相应的对象...
//        JSONObject personObject = (Person) jsonObject2.get("hjd");
//        JSONObject personObject = (JSONObject) jsonObject2.get("hjd");
        //第一种写法
        JSONObject personObject = jsonObject2.getJSONObject("hjd");
        //第二种写法
        person2.setId(personObject.getInt("id"));
        person2.setName(personObject.getString("name"));
        person2.setAddress(personObject.getString("address"));
        System.out.println("--------------->person2: " + person2);
        System.out.println("person2.getId(): " + person2.getId() + ",person2.getAddress(): " + person2.getAddress() + ",person2.getName()" + person2.getName());
        //解析基本数据类型
        int num = jsonObject2.getInt("int");
        System.out.println("---->num: " + num);
        //解析基本类型的数组数组
        JSONArray arrArray = jsonObject2.getJSONArray("arr");
        int size = arrArray.size();
        int arr2[] = new int[size];
        for (int i = 0; i < size; ++i) {
            arr2[i] = arrArray.getInt(i);
        }
        System.out.println(Arrays.toString(arr2));
        //解析List<String>
        JSONArray strArray = jsonObject2.getJSONArray("strList");
        List<String> strList2 = new ArrayList<String>();
        int size2 = strArray.size();
        for (int i = 0; i < size2; ++i) {
            strList2.add(strArray.getString(i));
        }
        System.out.println("---------->" + strList2);
        //解析List<Person>
        JSONArray personArray = jsonObject2.getJSONArray("personList");
        List<Person> personList2 = new ArrayList<Person>();
        int size3 = personArray.size();
        for (int i = 0; i < size3; ++i) {
            JSONObject jObject = personArray.getJSONObject(i);
            Person p = new Person();
            p.setId(jObject.getInt("id"));
            p.setName(jObject.getString("name"));
            p.setAddress(jObject.getString("address"));
            personList2.add(p);
        }
        System.out.println("----------->" + personList2);
        //解析List<Map>
        JSONArray mapArray = jsonObject2.getJSONArray("mapList");
        List<Map> mapList2 = new ArrayList<Map>();
        int size4 = mapArray.size();
        for (int i = 0; i < size4; ++i) {
            JSONObject jo = mapArray.getJSONObject(i);
            Map m = new HashMap<String, Integer>();
            m.put("" + i, jo.getInt("" + i));
            mapList2.add(m);
        }
        System.out.println("--------------->" + mapList2);
    }
}