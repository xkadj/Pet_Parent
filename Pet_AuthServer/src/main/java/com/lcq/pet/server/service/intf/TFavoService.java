package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TFavo;
import com.lcq.pet.server.entity.TNote;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TFavoService {
    /*新增*/
    R save(TFavo tFavo);
    /*删除*/
    R delById(int id);
    /*查询全部*/
    R all();

    //根据用户id查询该用户收藏数量
    int getFavoNumByUserId(int userId);

    //根据用户id查询该用户收藏列表
    List<TNote> queryAllFavoByUserId(int userId);
}