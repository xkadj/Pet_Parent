package com.lcq.pet.common.third;

import redis.clients.jedis.Jedis;


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
        jedis=new Jedis("192.168.164.129",6379);
        jedis.auth("root");
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

    //添加一个list集合
    public void addList(String key,String...strs){
         jedis.lpush(key,strs);
    }

    //设置一个key的存活时间
    public void setKeyTime(String key, int second){
         jedis.expire(key,second);
    }

    public long getSize(String key){
        return jedis.llen(key);
    }




}
