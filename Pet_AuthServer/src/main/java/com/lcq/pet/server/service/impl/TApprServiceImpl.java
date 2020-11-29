package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TAppr;
import com.lcq.pet.server.dao.TApprDao;
import com.lcq.pet.server.service.intf.TApprService;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TApprServiceImpl implements TApprService{

    @Autowired
    private TApprDao tApprDao;

    @Override
    public R save(TAppr tAppr){
        if(tApprDao.insert(tAppr)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tApprDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tApprDao.all());
    }

    @Override
    public int getApprNumByUserId(int userId) {
        return tApprDao.getApprNumByUserId(userId);
    }
}
