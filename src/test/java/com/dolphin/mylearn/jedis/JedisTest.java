package com.dolphin.mylearn.jedis;

import com.dolphin.mylearn.springboot.util.PrintUtil;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.Arrays;
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
    public void testIncreSave(){
        Jedis jedis = new Jedis("localhost");
        PrintUtil.print(jedis.get("age"));
        jedis.incr("age");
        PrintUtil.print(jedis.get("age"));
        jedis.incr("age");
        PrintUtil.print(jedis.get("age"));
    }

    @Test
    public void testMapSave(){
        Jedis jedis = new Jedis("localhost");
//        jedis

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

    @Test
    public void testShardingJedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        JedisShardInfo jedisShardInfo1 = new JedisShardInfo("localhost");
        ShardedJedisPool pool = new ShardedJedisPool(poolConfig, Arrays.asList(jedisShardInfo1));
        ShardedJedis jedis = pool.getResource();
        PrintUtil.print(jedis.get("name"));
        jedis.close();

    }

}
