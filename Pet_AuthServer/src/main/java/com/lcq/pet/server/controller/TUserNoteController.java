package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TUserNote;
import com.lcq.pet.server.service.intf.TUserNoteService;

import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tUserNote")
public class TUserNoteController {
    @Autowired
    private TUserNoteService tUserNoteService;
    //新增
    @PostMapping("/add.do")
    public R save(@RequestBody TUserNote tUserNote){
        return tUserNoteService.save(tUserNote);
    }

    //查询
    @GetMapping("/all.do")
    public R all(){
        return tUserNoteService.all();
    }

    //根据用户id查询该用户的所有笔记
    @GetMapping("/queryAllNotesByUserId.do")
    public List<TNote> queryAllNotesByUserId(int userId){
        if (userId != 0){
            return tUserNoteService.queryAllNotsByUserId(userId);
        }
        return null;
    }
}