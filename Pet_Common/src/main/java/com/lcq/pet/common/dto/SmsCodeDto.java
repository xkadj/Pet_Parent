package com.lcq.pet.common.dto;

import lombok.Data;

/**
 * @program: Health_Parent
 * @description:
 * @author:
 * @create: 2020-11-26 16:01
 */
@Data
public class SmsCodeDto {
    private String phone;
    private int code;
}
