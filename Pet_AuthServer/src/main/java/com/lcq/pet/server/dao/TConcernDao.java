package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TConcern;
import com.lcq.pet.server.entity.TConcernUsers;
import org.apache.ibatis.annotations.*;
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
    @Delete("delete from t_concern where u_id_from=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_concern")
    List<TConcern> all();

    @Select("select count(*) from t_concern where u_id_from=#{userId} or (u_id_to = #{userId} and c_flag = 1);")
    int getConcernNumByUserId(int userId);

    //查询该用户是否已经关注了该用户
    @Select("select * from t_concern where u_id_from = #{uIdFrom} and u_id_to = #{uIdTo}")
    TConcern isConcern(int uIdFrom,int uIdTo);

    //将双向关注改成反向单向关注
    @Update("update t_concern set u_id_from = #{uIdTo},u_id_to = #{uIdFrom}, c_flag = 0  where u_id_from = #{uIdFrom} and u_id_to = #{uIdTo}")
    int makeConcernRevers(int uIdFrom, int uIdTo);

    //将单向关注改为双向关注
    @Update("update t_concern set u_id_from = #{uIdFrom},u_id_to = #{uIdTo}, c_flag = 1  where u_id_from = #{uIdFrom} and u_id_to = #{uIdTo}")
    int makeConcernBoth(int uIdFrom, int uIdTo);

    //查询用户关注的所有用户id，返回一个对象集合
    @Select("select c1.u_id_from,c2.u_id_to from t_concern c1,t_concern c2 where (c1.u_id_to = #{userId} and c1.c_flag = 1) and c2.u_id_from = #{userId}")
    List<TConcernUsers> queryAllUserIdByUserId(int userId);

}