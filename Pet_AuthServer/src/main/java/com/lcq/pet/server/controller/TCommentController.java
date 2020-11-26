package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TComment;
import com.lcq.pet.server.service.intf.TCommentService;
/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tComment")
public class TCommentController {
    @Autowired
    private TCommentService tCommentService;
    //新增
    @PostMapping("/add.do")
    public R save(@RequestBody TComment tComment){
        return tCommentService.save(tComment);
    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tCommentService.delById(id);
    }
    //查询
    @GetMapping("/all.do")
    public R all(){
        return tCommentService.all();
    }
}