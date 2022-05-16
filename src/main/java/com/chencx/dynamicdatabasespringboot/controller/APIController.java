package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.service.APIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/api")
public class APIController {
    @Resource
    private APIService apiService;

    //适合查询多条数据
    @PostMapping("/getList")
    public List getList(String sqlName) {

        return apiService.getList(sqlName);
    }

    //适合查询单条数据
    @PostMapping("/getMap")
    public Map getMap(String sqlName) {
        return apiService.getMap(sqlName);
    }

}
