package com.lcq.pet.server.controller;

import com.lcq.pet.common.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class test {

    @GetMapping("/test.do")
    public R test(){
        return R.ok();
    }
}
