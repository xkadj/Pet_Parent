package com.lcq.pet.test;

import com.lcq.pet.common.util.EncryptUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: Health_Parent
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-11-25 16:19
 */
public class Pass_Test {
    @Test
    public void t1(){
        //生成密钥
        String key= EncryptUtil.createAESKey();
        System.out.println(key);
        String p1="123456";
        String m=EncryptUtil.aesenc(key,p1);
        System.out.println(m);
        System.out.println("解密："+EncryptUtil.aesdec(key,m));
    }
}
