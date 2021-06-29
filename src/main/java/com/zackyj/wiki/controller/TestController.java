package com.zackyj.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "ok";
    }

    @PostMapping("/hi")
    public String hi(String name){
        return "hi"+name;
    }
}
