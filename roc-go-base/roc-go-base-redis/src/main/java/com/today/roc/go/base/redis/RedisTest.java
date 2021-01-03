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
        String name = jedis.get("name");
        System.out.println(name);
    }
}
