package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TConcernUsers;
import com.lcq.pet.server.entity.TNote;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Repository
public interface TNoteDao {
    /*新增*/
    @Insert("insert into t_note (n_content,n_kind,n_time) values(#{n_content},#{n_kind},#{n_time})")
    int insert(TNote tNote);

    /*删除*/
    @Delete("delete from t_note where n_id=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_note")
    List<TNote> all();

    //插入一条笔记记录
    @Insert("insert into t_note (n_content,n_kind,n_time) values(#{n_content},#{n_kind},#{n_time})")
    @Options(useGeneratedKeys = true,keyProperty = "n_id")//标记获取自增主键的值
    int publishNote(TNote tNote);

    //查询所有笔记
    @Select("select * from t_note")
    List<TNote> queryAllNotes();


    //查询用户关注的用户的笔记
    @Select("select t_note.* from t_note,t_user_note where t_note.n_id = t_user_note.n_id  and t_user_note.u_id in ${userIdSet}")
    List<TNote> queryConcernUserNotesByUserId(String userIdSet);


}