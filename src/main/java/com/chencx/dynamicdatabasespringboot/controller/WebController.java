package com.chencx.dynamicdatabasespringboot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
@RequestMapping("/")
public class WebController {

    @RequestMapping("/doc")
    public String knife4jDoc(){
        return "redirect: doc.html";
    }
    @RequestMapping("/offlinedoc")
    public String offlineDoc(){
        return "doc.html";
    }
    @RequestMapping("/onlinedoc")
    public String onlineDoc(){
        return "redirect:https://console-docs.apipost.cn/preview/17de6bd4ab90cd51/6a78ffcb10822da4?target_id=ac9d3206-da6a-4b5a-b53a-53f57ab25a15#ac9d3206-da6a-4b5a-b53a-53f57ab25a15";
    }
    @RequestMapping("/panel")
    public String WebManage(){
        return "index";
    }
}
