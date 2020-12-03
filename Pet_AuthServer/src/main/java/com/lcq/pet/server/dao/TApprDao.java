package com.lcq.pet.server.dao;

import com.lcq.pet.server.entity.TAppr;
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
public interface TApprDao {
    /*新增*/
    @Insert("insert into t_appr (u_id_from,n_id) values(#{u_id_from},#{n_id})")
    int insert(TAppr tAppr);

    /*删除*/
    @Delete("delete from t_appr where id=#{id}")
    int deleteById(int id);
    /*查询全部*/
    @Select("select * from t_appr")
    List<TAppr> all();

    //根据用户id查询点赞的总数量
    @Select("select count(*) from t_appr where u_id_from = #{userId}")
    int getApprNumByUserId(int userId);

    //根据指定点笔记id获取该条笔记的点赞人的id
    @Select("select u_id_from from t_appr where n_id = #{noteId}")
    List<Integer> getAllUserIdByApprId(int noteId);

    //根据指定点笔记id获取该条笔记的点赞人的id
    @Delete("delete from t_appr where n_id = #{noteId} and u_id_from = #{userId}")
    void deleteNoteByUserIdAndApprId(int userId, int noteId);
}