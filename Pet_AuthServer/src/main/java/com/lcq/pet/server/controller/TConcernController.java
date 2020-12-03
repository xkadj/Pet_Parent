package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TConcern;
import com.lcq.pet.server.service.intf.TConcernService;
/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tConcern")
public class TConcernController {
    @Autowired
    private TConcernService tConcernService;
    //新增
    @PostMapping("/add.do")
    public R save(@RequestBody TConcern tConcern){
        return tConcernService.save(tConcern);
    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tConcernService.delById(id);
    }
    //查询
    @GetMapping("/all.do")
    public R all(){
        return tConcernService.all();
    }

    //关注
    @PostMapping("/concern.do")
    public R concern(TConcern concern){
        if (concern != null){
            return tConcernService.concern(concern);
        }
        return  R.fail("关注失败！");
    }

    @GetMapping("/getConcernNumByUserId.do")
    public int getConcernNumByUserId(int userId){
        if (userId != 0){
            return tConcernService.getConcernNumByUserId(userId);
        }
        return 0;
    }
}