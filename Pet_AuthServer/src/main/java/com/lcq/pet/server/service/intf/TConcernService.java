package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TConcern;
import org.apache.ibatis.annotations.Select;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TConcernService {
    /*新增*/
    R save(TConcern tConcern);
    /*删除*/
    R delById(int id);
    /*查询全部*/
    R all();


    //关注
    R concern(TConcern concern);

    //根据用户id查询该用户关注人数
    int getConcernNumByUserId(int userId);



}