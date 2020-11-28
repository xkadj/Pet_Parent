package com.lcq.pet.server.service.intf;

import com.lcq.pet.common.dto.UserDto;
import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.entity.TUser;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
public interface TUserService {
    /*新增*/
    R save(TUser tUser);
    /*删除*/
    R delById(int id);
    /*查询全部*/
    R all();

    //校验手机号是否存在
    R checkPhone(String phone);
    //密码找回
    R findPass(UserDto dto);

    //注册
    R registerV2(UserDto dto);

    //登陆
    R loginV2(UserDto dto);

}