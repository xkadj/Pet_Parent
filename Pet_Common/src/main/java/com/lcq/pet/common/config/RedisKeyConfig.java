package com.lcq.pet.common.config;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-25 17:17
 */
public class RedisKeyConfig {
    //登陆
    public static final String AUTH_TOKEN="health:authtoken:";//后面追加令牌
    public static final String AUTH_PHONE="health:authphone:";//后面追加手机号
    public static final int AUTH_TIME=1800;//有效期

    //记录账号挤掉 Hash类型 feild:存储令牌 value:挤掉信息
    public static final String AUTH_OUT="health:auth:outs";//

    //记录短信注册验证码
    public static final String SMS_RCODE="health:sms:register:";//后面追加手机号
    public static final int SMS_RTIME=600;//有效期

    //记录密码输入错误的list在再redis里
    public static final String LOGIN_FALSE="health:login:phone:";//后面追加手机号


}
