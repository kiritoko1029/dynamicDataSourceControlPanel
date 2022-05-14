package com.chencx.dynamicdatabasespringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/web")
public class WebController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        return "sucess";
    }
}
