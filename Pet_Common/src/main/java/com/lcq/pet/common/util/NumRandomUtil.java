package com.lcq.pet.common.util;

import java.util.Random;

/**
 * @program: Kataba
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-10-20 09:57
 */
public class NumRandomUtil {
    //生成随机数字，指定位数
    public static int randomNum(int len){
        Random random=new Random();
        //4 ：1000-9999 0-8999  +1000
        //9000  +1000
        double d=Math.pow(10,len-1);
        return random.nextInt((int)(Math.pow(10,len)-d))+(int)d;
    }
}
