package com.lcq.pet.common.third;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @program: Health_Parent
 * @description: 单例设计模式
 * @author:
 * @create: 2020-11-25 17:12
 */
public class JedisUtil {
    private Jedis jedis;
    private static JedisUtil util=new JedisUtil();
    private JedisUtil(){
        jedis=new Jedis("39.105.189.141",6380);
        jedis.auth("qfjava");
    }
    public static JedisUtil getInstance(){
        return util;
    }
    //新增
    public void addStr(String key,String val){
        jedis.set(key, val);
    }
    public void addStrEx(String key, String val, int seconds){
        jedis.setex(key,seconds,val);
    }
    public void addHash(String key,String field,String value){
        jedis.hset(key, field, value);
    }
    //删除
    //修改
    //查询
    public String getStr(String key){
        return jedis.get(key);
    }
    public String getHash(String key,String field){
        return jedis.hget(key, field);
    }
    public boolean checkHash(String key,String field){
        return jedis.hexists(key, field);
    }
    //系统
    public boolean ishave(String key){
        return jedis.exists(key);
    }
    public boolean delKeys(String... keys){
        return jedis.del(keys)>0;
    }

}
