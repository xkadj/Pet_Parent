package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
}