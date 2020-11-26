package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TNote;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TNoteService {
    /*新增*/
    R save(TNote tNote);
    /*删除*/
    R delById(int id);
    /*查询全部*/
    R all();
}