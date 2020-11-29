package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TComment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TCommentService {
    /*新增*/
    R save(TComment tComment);
    /*删除*/
    R delById(int id);
    /*查询全部*/
    R all();

    //评论
    public R comment(TComment tComment);

    //查询对该条笔记的评论
    List<TComment> queryCommentToNoteByNoteId(int noteId);

    //查询对该条评论的评论
    List<TComment> queryCommentToCommentByCommentId(int cid);

}