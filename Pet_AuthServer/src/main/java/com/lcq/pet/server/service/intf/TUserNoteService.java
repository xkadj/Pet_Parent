package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TNote;
import com.lcq.pet.server.entity.TUserNote;

import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TUserNoteService {
    /*新增*/
    R save(TUserNote tUserNote);

    /*查询全部*/
    R all();


    //根据用户id查询该用户的所有笔记
    List<TNote> queryAllNotsByUserId(int userId);
}