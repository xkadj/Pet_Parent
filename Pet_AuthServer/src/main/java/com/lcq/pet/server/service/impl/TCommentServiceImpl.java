package com.lcq.pet.server.service.impl;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lcq.pet.server.entity.TComment;
import com.lcq.pet.server.dao.TCommentDao;
import com.lcq.pet.server.service.intf.TCommentService;

import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@Service
public class TCommentServiceImpl implements TCommentService{

    @Autowired
    private TCommentDao tCommentDao;

    @Override
    public R save(TComment tComment){
        if(tCommentDao.insert(tComment)>0){
            return R.ok();
        }else{
            return R.fail("新增失败");
        }
    }
    @Override
    public R delById(int id){
        if(tCommentDao.deleteById(id)>0){
            return R.ok();
        }else{
            return R.fail("删除失败");
        }
    }
    @Override
    public R all(){
       return R.ok(tCommentDao.all());
    }

    @Override
    public R comment(TComment tComment) {
        return tCommentDao.comment(tComment) > 0 ?  R.ok() : R.fail("评论失败");
    }

    @Override
    public List<TComment> queryCommentToNoteByNoteId(int noteId) {
        return tCommentDao.queryCommentToNoteByNoteId(noteId);
    }

    @Override
    public List<TComment> queryCommentToCommentByCommentId(int cid) {
        return tCommentDao.queryCommentToCommentByCommentId(cid);
    }
}
