package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TComment;
import com.lcq.pet.server.service.intf.TCommentService;

import java.util.List;

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

    //评论
    @PostMapping("/comment.do")
    public R comment(TComment tComment){
        if(tComment != null){
           return tCommentService.comment(tComment);
        }

        return R.fail("评论失败");
    }

    //查询对该条笔记的评论
    @GetMapping("/queryCommentToNoteByNoteId.do")
    public List<TComment> queryCommentToNoteByNoteId(int id){
        return tCommentService.queryCommentToNoteByNoteId(id) ;
    }

    //查询对该条评论的评论
    @GetMapping("/queryCommentToCommentByCommentId.do")
    public List<TComment> queryCommentToCommentByCommentId(int cid){
        return tCommentService.queryCommentToCommentByCommentId(cid) ;
    }
}