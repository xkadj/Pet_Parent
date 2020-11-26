package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TNote;
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
public interface TNoteDao {
    /*新增*/
    @Insert("insert into t_note (n_content,n_kind,n_time) values(#{n_content},#{n_kind},#{n_time})")
    int insert(TNote tNote);

    /*删除*/
    @Delete("delete from t_note where id=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_note")
    List<TNote> all();
}