package com.lcq.pet.server.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Data
public class TUser implements Serializable {
    private Integer u_id;
    private String u_name;
    private String u_password;
    private String u_sex;
    private String u_phone;
    private String u_email;
    private Integer u_flag;
    private String u_img;
    private String u_addr;
    private String u_sign;
}