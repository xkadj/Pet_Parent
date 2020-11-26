package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TUser;
import com.lcq.pet.server.dao.TUserDao;
import com.lcq.pet.server.service.intf.TUserService;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TUserServiceImpl implements TUserService{

    @Autowired
    private TUserDao tUserDao;

    @Override
    public R save(TUser tUser){
        if(tUserDao.insert(tUser)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tUserDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tUserDao.all());
    }
}
