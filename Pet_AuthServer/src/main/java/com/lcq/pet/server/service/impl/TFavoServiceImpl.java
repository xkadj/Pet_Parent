package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TFavo;
import com.lcq.pet.server.dao.TFavoDao;
import com.lcq.pet.server.service.intf.TFavoService;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TFavoServiceImpl implements TFavoService{

    @Autowired
    private TFavoDao tFavoDao;

    @Override
    public R save(TFavo tFavo){
        if(tFavoDao.insert(tFavo)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tFavoDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tFavoDao.all());
    }
}
