package com.lcq.pet.server.controller;

import com.lcq.pet.common.dto.UserDetialDto;
import com.lcq.pet.common.dto.UserDto;
import com.lcq.pet.common.dto.UserFindPass;
import com.lcq.pet.common.dto.UserUpdatePassDto;
import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.service.intf.TUserService;
/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tUser")
public class TUserController {
    @Autowired
    private TUserService tUserService;
    //新增
//    @PostMapping("/add.do")
//    public R save(@RequestBody TUser tUser){
//        return tUserService.save(tUser);
//    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tUserService.delById(id);
    }

//    //查询
//    @GetMapping("/all.do")
//    public R all(){
//        return tUserService.all();
//    }

    //注册
    @PostMapping("/register.do")
    public R register(@RequestBody UserDto dto){
        return tUserService.registerV2(dto);
    }

    //登陆
    @PostMapping("/login.do")
    public R login(@RequestBody UserDto dto){
        return tUserService.loginV2(dto);
    }

    //校验
    @GetMapping("/checkphone.do")
    public R check(String phone){
        return tUserService.checkPhone(phone);
    }

    //密码找回
    @PostMapping("/findpass.do")
    public R finaPass(@RequestBody UserFindPass dto){
        return tUserService.findPass(dto);
    }

    //修改密码
    @PostMapping("/changepass.do")
    public R changePass(@RequestBody UserUpdatePassDto dto){
        return tUserService.changePass(dto);
    }

    //修改个人信息
    @PostMapping("/updateUserDetail.do")
    public R updateUserDetail(@RequestBody UserDetialDto dto){
        return tUserService.updateUserDetail(dto);
    }

}