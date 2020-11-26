package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TUserNote;
import com.lcq.pet.server.dao.TUserNoteDao;
import com.lcq.pet.server.service.intf.TUserNoteService;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TUserNoteServiceImpl implements TUserNoteService{

    @Autowired
    private TUserNoteDao tUserNoteDao;

    @Override
    public R save(TUserNote tUserNote){
        if(tUserNoteDao.insert(tUserNote)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tUserNoteDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tUserNoteDao.all());
    }
}
