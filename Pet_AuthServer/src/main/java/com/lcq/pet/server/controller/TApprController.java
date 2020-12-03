package com.lcq.pet.server.controller;

import com.lcq.pet.common.config.SystemConfig;
import com.lcq.pet.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lcq.pet.server.entity.TAppr;
import com.lcq.pet.server.service.intf.TApprService;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 码起 自动生成代码
 * @author: zkh
 * @create: 2020-11-26 19:20:19
 */
@RestController
@RequestMapping("/api/tAppr")
public class TApprController {
    @Autowired
    private TApprService tApprService;
    //新增
    @PostMapping("/add.do")
    public R save(@RequestBody TAppr tAppr){
        return tApprService.save(tAppr);
    }
    //删除
    @DeleteMapping("/del.do")
    public R del(int id){
        return tApprService.delById(id);
    }
    //查询
    @GetMapping("/all.do")
    public R all(){
        return tApprService.all();
    }

    //根据用户的id查询该用户点赞的总数量
    @GetMapping("/getConcernNumByUserId.do")
    public int getConcernNumByUserId(int userId){
        if (userId != 0){
            return tApprService.getApprNumByUserId(userId);
        }
        return  0;
    }

    //做评论
    @GetMapping("/makeAppr.do")
    public R makeAppr(HttpServletRequest request,TAppr tAppr){
        return tApprService.makeAppr(request.getHeader(SystemConfig.TOKEN_HEA),tAppr.getN_id());
    }
}