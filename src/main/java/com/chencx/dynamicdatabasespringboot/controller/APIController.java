package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.service.APIService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@EnableAutoConfiguration
@RequestMapping("/api")
public class APIController {
    @Resource
    private APIService apiService;

    @RequestMapping("/hello")
    public String toHello(HttpServletRequest request){
        return "hello";
    }

    @GetMapping("/getLineList")
    @ResponseBody
    public List getLineList(String dataBase){
        return apiService.selectAll(dataBase);
    }

}
