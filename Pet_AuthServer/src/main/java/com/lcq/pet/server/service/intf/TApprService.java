package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TAppr;
import org.apache.ibatis.annotations.Select;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TApprService {
    /*新增*/
    R save(TAppr tAppr);
    /*删除*/
    R delById(int id);
    /*查询全部*/
    R all();

    //根据用户id查询点赞的总数量
    int getApprNumByUserId(int userId);
}