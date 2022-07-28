package com.th.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: vhr
 * @description:
 * @author: xiaokaixin
 **/
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/emp/basic/hello")
    public String hello2(){
        return "/emp/basic/hello";
    }

    @GetMapping("/emp/advanced/hello")
    public String hello3(){
        return "/emp/adv/hello";
    }


}
