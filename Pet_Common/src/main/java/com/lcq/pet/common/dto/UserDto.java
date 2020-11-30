package com.lcq.pet.common.dto;

import lombok.Data;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-25 16:11
 */
@Data
public class UserDto {
    private String u_phone;
    private String u_password;
    private Integer u_flag;
    private String code;
}
