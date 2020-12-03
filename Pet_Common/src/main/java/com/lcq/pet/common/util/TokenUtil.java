package com.lcq.pet.common.util;

import com.lcq.pet.common.config.RedisKeyConfig;
import com.lcq.pet.common.third.JedisUtil;

/**
 * @program: Health_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-11-30 14:37
 */
public class TokenUtil {
    //通过令牌 获取用户id
    public static int getUid(String token){
        String k= RedisKeyConfig.AUTH_TOKEN+token;
        if(JedisUtil.getInstance().ishave(k)) {
            String id=JedisUtil.getInstance().getStr(k);
            if(StrUtil.checkNoEmpty(id) && id.matches("[0-9]{1,}")){
                return Integer.parseInt(id);
            }
        }
        return 0;
    }
}
