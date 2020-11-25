package com.lcq.pet.common.vo;

import com.lcq.pet.common.config.SystemCodeConfig;
import lombok.Data;

/**
 * @program:
 * @description:  接口统一返回结果
 * @author:
 * @create: 2020-11-25 10:49
 */
@Data
public class R {
    private int code;
    private String msg;
    private Object data;

    public R() {
    }

    public R(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static R ok(){
        return new R(SystemCodeConfig.OK,"OK",null);
    }
    public static R ok(Object data){
        return new R(SystemCodeConfig.OK,"OK",data);
    }
    public static R fail(){
        return new R(SystemCodeConfig.FAIL,"fail",null);
    }
    public static R fail(String msg){
        return new R(SystemCodeConfig.FAIL,msg,null);
    }
    public static R ok(String msg){
        return new R(SystemCodeConfig.OK,msg,null);
    }
}