package com.today.roc.go.base.redis;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年12月30日 22:48*
 * log.info()
 */
public class RedisTest {

    public static void main(String[] args) {
        JedisUtils jedis = new JedisUtils();
        String name = jedis.get("str:key");
        System.out.println(name);
        slowLog();
    }


    /**
     * 慢查询 增加大量key 通过keys *查询
     */
    public static void slowLog(){
        JedisUtils jedis = new JedisUtils();
        String name = jedis.get("str:key");
        for (int i = 0; i < 50000; i++) {
            jedis.set("str:"+i,i+"");
            jedis.hset("hash:"+i,"user",i+"");
        }
    }

    void testString(){


    }


    /**
     * 将用户抢红包黑名单（防重抢）的数据类型改为set类型，lua脚本内容也要相应调用，完成后把代码各修改处，截图给我；
     */



}
