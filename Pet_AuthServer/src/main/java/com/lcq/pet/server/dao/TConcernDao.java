package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TConcern;
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
public interface TConcernDao {
    /*新增*/
    @Insert("insert into t_concern (u_id_from,u_id_to,c_flag) values(#{u_id_from},#{u_id_to},#{c_flag})")
    int insert(TConcern tConcern);

    /*删除*/
    @Delete("delete from t_concern where u_id_to=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_concern")
    List<TConcern> all();

    @Select("select count(*) from t_concern where u_id_from=#{userId} or (u_id_to = #{userId} and c_flag = 1);")
    int getConcernNumByUserId(int userId);
}