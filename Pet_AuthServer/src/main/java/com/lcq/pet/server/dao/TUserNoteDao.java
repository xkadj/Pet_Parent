package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TUserNote;
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
public interface TUserNoteDao {
    /*新增*/
    @Insert("insert into t_user_note (u_id,n_id) values(#{u_id},#{n_id})")
    int insert(TUserNote tUserNote);

    /*删除*/
    @Delete("delete from t_user_note where id=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_user_note")
    List<TUserNote> all();
}