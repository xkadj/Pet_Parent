package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import com.lcq.pet.server.dao.TConcernDao;
import com.lcq.pet.server.dao.TNoteDao;
import com.lcq.pet.server.entity.TConcernUsers;
import com.lcq.pet.server.entity.TUserNote;
import com.lcq.pet.server.service.intf.TUserNoteService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TNote;
import com.lcq.pet.server.service.intf.TNoteService;

import java.util.HashSet;
import java.util.List;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tNote")
public class TNoteController {
    @Autowired
    private TNoteService tNoteService;

    @Autowired
    private TNoteDao tNoteDao;

    @Autowired
    private TConcernDao tConcernDao;

    @Autowired
    private TUserNoteService userNoteService;
    //新增
    @PostMapping("/add.do")
    public R save(@RequestBody TNote tNote){
        return tNoteService.save(tNote);
    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tNoteService.delById(id);
    }
    //查询
    @GetMapping("/all.do")
    public R all(){
        return tNoteService.all();
    }

    @PostMapping("/publish.do")
    private R publishNote(TNote tNote, int userID){
        if (tNote != null){
            tNoteService.publishNote(tNote);
            TUserNote tUserNote = new TUserNote();
            tUserNote.setN_id(tNote.getN_id());
            tUserNote.setU_id(userID);
            //创建笔记的同时也在用户-笔记表中插入一条记录，进行关联
            userNoteService.save(tUserNote);
            return R.ok();
        }
        return R.fail("错误");
    }

    @GetMapping("/queryAllNotes")
    public List<TNote> queryAllNotes(){
        return tNoteService.queryAllNotes();
    }

    @GetMapping("/queryConcernUserNotesByUserId.do")
    public List<TNote> queryConcernUserNotesByUserId(int userId){
        return tNoteService.queryConcernUserNotesByUserId(userId);
    }

    //删除该用户的指定笔记
    @GetMapping("/deleteNoteByUserIdAndNoteId.do")
    public R deleteNoteByUserIdAndNoteId(int userId, int noteId){
        return tNoteService.deleteNoteByUserIdAndNoteId(userId,noteId);
    }

}