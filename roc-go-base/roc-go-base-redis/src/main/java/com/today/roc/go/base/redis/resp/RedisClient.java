package com.today.roc.go.base.redis.resp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.today.roc.go.base.redis.constants.JedisConstants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2021年01月24日 21:02*
 * log.info()
 */
public class RedisClient {


    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), JedisConstants.ip, JedisConstants.port, 100000, JedisConstants.auth);
        Jedis jedis = pool.getResource();
        //使用Pipeline 批量执行
        Pipeline pipelined = jedis.pipelined();
        wrapPipelineDel(pipelined);
        //wrapPipelineAdd(pipelined);
        System.out.println(JSONObject.toJSONString(pipelined));
        //执行-无结果返回
        //pipelined.sync();
        //执行并获取返回结果
        List<Object> objects = pipelined.syncAndReturnAll();
        //pipelined.multi();//开始事务
        //pipelined.exec();//提交事务
        //pipelined.watch();//取消事务
        //jedis.multi();
        //jedis.watch()
        System.out.println(JSONObject.toJSONString(objects));
    }

    public static void wrapPipelineDel(Pipeline pipelined) {
        for (int i = 0; i < 1000; i++) {
            pipelined.del("str:" + i);
        }
    }

    public static void wrapPipelineAdd(Pipeline pipelined) {
        for (int i = 0; i < 1000; i++) {
            pipelined.set("str:" + i, i + "");
        }
    }

    public static void fakeRedisClient() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("user:name", "cp");
        jedis.close();
    }


}
