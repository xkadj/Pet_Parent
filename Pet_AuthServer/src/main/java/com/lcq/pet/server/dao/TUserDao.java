package com.lcq.pet.server.dao;

import com.lcq.pet.common.dto.UserDetialDto;
import com.lcq.pet.common.dto.UserFindPass;
import com.lcq.pet.common.dto.UserUpdatePassDto;
import com.lcq.pet.server.entity.TUser;
import org.apache.ibatis.annotations.*;
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

    @Select("select * from t_user where u_phone=#{phone}")
    TUser userdetail(String phone);


    //检验手机号
    @Select("select * from t_user where u_phone=#{phone}")
    TUser selectByPhone(String phone);

    //修改密码
    @Update("update t_user set u_password=#{u_newpassword} where u_phone=#{u_phone}")
    int changePass(UserUpdatePassDto dto);

    //找回密码
    @Update("update t_user set u_password=#{u_password} where u_phone=#{u_phone}")
    int updatePass(UserFindPass dto);

    //注册
    @Insert("insert into t_user (u_phone,u_password,u_flag) values(#{u_phone},#{u_password},1)")
    @Options(useGeneratedKeys = true,keyProperty = "u_id")//标记获取自增主键的值
    int insertId(TUser tUser);

    //修改个人信息
    @Update("update t_user set u_name=#{u_name}, u_sex=#{u_sex}, u_email=#{u_email}, u_img=#{u_img},u_addr=#{u_addr},u_sign=#{u_sign}  where u_phone=#{u_phone}")
    int updateDetail(UserDetialDto dto);

    //冻结用户：flag=0
    @Update("update t_user set u_flag = 0 where u_phone = #{phone}")
    void frizeUser(String phone);

      //解冻用户：flag=1
    @Update("update t_user set u_flag = 1 where u_phone = #{phone}")
    void activeUser(String phone);


}