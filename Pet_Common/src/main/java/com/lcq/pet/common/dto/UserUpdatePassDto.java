package com.lcq.pet.common.dto;


import lombok.Data;

@Data
public class UserUpdatePassDto {
    private String u_phone;
    private String u_password;
    private String u_newpassword;

}
