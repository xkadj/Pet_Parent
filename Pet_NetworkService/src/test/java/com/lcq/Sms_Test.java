package com.lcq;

import com.lcq.pet.common.util.NumRandomUtil;
import com.lcq.pet.server.util.AliSmsUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-26 15:48
 */
public class Sms_Test {
    @Test
    public void t1(){
        int code= NumRandomUtil.randomNum(6);
        System.out.println(code);
        System.out.println("短信发送："+ AliSmsUtil.sendSmsCode("17737495073",code));
    }
}
