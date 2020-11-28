package com.lcq.pet.server.etype;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-26 09:46
 */
public enum UserLogType {
    注册(1),密码找回(2);
    private int val;
    private UserLogType(int v){
        val=v;
    }

    public int getVal() {
        return val;
    }
}
