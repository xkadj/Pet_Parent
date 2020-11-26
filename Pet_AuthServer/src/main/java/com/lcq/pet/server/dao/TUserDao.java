package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TUser;
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
public interface TUserDao {
    /*新增*/
    @Insert("insert into t_user (u_name,u_password,u_sex,u_phone,u_email,u_flag,u_img,u_addr,u_sign) values(#{u_name},#{u_password},#{u_sex},#{u_phone},#{u_email},#{u_flag},#{u_img},#{u_addr},#{u_sign})")
    int insert(TUser tUser);

    /*删除*/
    @Delete("delete from t_user where u_id=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_user")
    List<TUser> all();
}