package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TFavo;
import com.lcq.pet.server.service.intf.TFavoService;
/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tFavo")
public class TFavoController {
    @Autowired
    private TFavoService tFavoService;
    //新增
    @PostMapping("/add.do")
    public R save(@RequestBody TFavo tFavo){
        return tFavoService.save(tFavo);
    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tFavoService.delById(id);
    }
    //查询
    @GetMapping("/all.do")
    public R all(){
        return tFavoService.all();
    }
}