package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.dao.TConcernDao;
import com.lcq.pet.server.dao.TUserDao;
import com.lcq.pet.server.dao.TUserNoteDao;
import com.lcq.pet.server.entity.TConcernUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TNote;
import com.lcq.pet.server.dao.TNoteDao;
import com.lcq.pet.server.service.intf.TNoteService;

import java.util.ArrayList;
import java.util.HashSet;
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

    @Autowired
    private TUserNoteDao tUserNoteDao;

    @Autowired
    private TConcernDao tConcernDao;

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

    //查询用户关注的用户的笔记
    @Override
    public List<TNote> queryConcernUserNotesByUserId(int userId) {
        List<TConcernUsers> tConcernUsers = tConcernDao.queryAllUserIdByUserId(userId);
        HashSet<Integer> hashSet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        for (TConcernUsers concernUsers:tConcernUsers ) {
            hashSet.add(concernUsers.getU_id_from());
            hashSet.add(concernUsers.getU_id_to());
        }
        stringBuilder.append("(");
        for (Integer id:hashSet) {
            stringBuilder.append(id+",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(")");
        return tNoteDao.queryConcernUserNotesByUserId(stringBuilder.toString());

    }

    //删除该用户的指定笔记
    @Override
    public R deleteNoteByUserIdAndNoteId(int userId, int noteId) {
        //先删除笔记
        tNoteDao.deleteById(noteId);
        //在删除用户笔记关联
        tUserNoteDao.deleteById(userId,noteId);
        return R.ok("删除成功");
    }


}
