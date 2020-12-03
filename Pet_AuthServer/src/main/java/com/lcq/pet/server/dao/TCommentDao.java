package com.lcq.pet.server.dao;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Repository
public interface TCommentDao {
    /*新增*/
    @Insert("insert into t_comment (c_content,c_time,c_userid,c_to_cid,c_to_nid) values(#{c_content},#{c_time},#{c_userid},#{c_to_cid},#{c_to_nid})")
    int insert(TComment tComment);

    /*删除*/
    @Delete("delete from t_comment where id=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_comment")
    List<TComment> all();

    //评论
    @Insert("insert into t_comment (c_content,c_time,c_userid,c_to_cid,c_to_nid) values(#{c_content},#{c_time},#{c_userid},#{c_to_cid},#{c_to_nid})")
    @Options(useGeneratedKeys = true,keyProperty = "c_id")//标记获取自增主键的值
    int comment(TComment tComment);


    //查询对该条笔记的评论
    @Select("select c.* from t_note n,t_comment c where n.n_id = c.c_to_nid and n.n_id = #{noteId}")
    List<TComment> queryCommentToNoteByNoteId(int noteId);

    //查询对该条评论的评论
    @Select("select c.* from t_comment c1,t_comment c where c1.c_id = c.c_to_cid  and c1.c_id = #{noteId}")
    List<TComment> queryCommentToCommentByCommentId(int cid);




}