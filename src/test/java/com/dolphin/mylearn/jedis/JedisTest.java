package com.dolphin.mylearn.jedis;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

/**
 * Created by jichuan.wang on 2017/10/10.
 */
public class JedisTest {
    @Test
    public void testSave(){
        Jedis jedis = new Jedis("localhost");
        jedis.set("name","wang");
        jedis.mset("haha","111","hehe","222");
        jedis.append("name"," jichuan");
        PrintUtil.print(jedis.dbSize());
        PrintUtil.print(jedis.getDB());
        List<String> result = jedis.mget("haha","hehe");
        PrintUtil.print(result);
    }

    @Test
    public void testJedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        JedisPool jedisPool = new JedisPool(poolConfig,"localhost");
        Jedis jedis = jedisPool.getResource();
        PrintUtil.print(jedis.get("name"));
        jedis.close();

    }

}
