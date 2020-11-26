package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TUser;
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
    @PostMapping("/add.do")
    public R save(@RequestBody TUser tUser){
        return tUserService.save(tUser);
    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tUserService.delById(id);
    }
    //查询
    @GetMapping("/all.do")
    public R all(){
        return tUserService.all();
    }
}