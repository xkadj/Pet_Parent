package com.lcq.pet.common.util;

/**
 * @program: Kataba
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-10-19 15:48
 */
public class StrUtil {
    /**
     * 校验是否为空
     * @return true 非空  false 空*/
    public static boolean checkNoEmpty(String... strs){
        boolean r=true;
        for(String s:strs){
            if(s==null || s.length()==0){
                r=false;
                break;
//                continue;//跳出本次循环 结束本次循环
//                break;//跳出当前最近的循环，结束最近的循环体
//                return ;//结束方法
            }
        }
        return r;
    }
}
