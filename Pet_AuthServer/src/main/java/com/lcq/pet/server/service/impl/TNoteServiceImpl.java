package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TNote;
import com.lcq.pet.server.dao.TNoteDao;
import com.lcq.pet.server.service.intf.TNoteService;

import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TNoteServiceImpl implements TNoteService{

    @Autowired
    private TNoteDao tNoteDao;

    @Override
    public R save(TNote tNote){
        if(tNoteDao.insert(tNote)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tNoteDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tNoteDao.all());
    }

    @Override
    public int publishNote(TNote tNote) {
        return tNoteDao.publishNote(tNote);
    }

    @Override
    public List<TNote> queryAllNotes() {
        return tNoteDao.queryAllNotes();
    }
}
