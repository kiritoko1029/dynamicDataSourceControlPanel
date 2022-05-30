package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.service.APIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.devtools.restart.FailureHandler;
import org.springframework.boot.devtools.restart.Restarter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/rest")
public class APIController {
    @Resource
    private APIService apiService;
    @Autowired
    ApplicationContext applicationContext;
    //适合查询多条数据
    @PostMapping("/query/{sqlName}")
    public List getList(@PathVariable String sqlName,@RequestBody(required = false) Map<String,String> args) {

        return apiService.getList(sqlName,args);
    }
    @GetMapping("/reboot")
    public int  reboot(){
        Restarter restarter = Restarter.getInstance();
        restarter.restart(new FailureHandler() {
            public Outcome handle(Throwable failure) {
                log.error("当前系统出现问题，无法重启项目...........");
                return FailureHandler.Outcome.ABORT;
            }

        });
        return 1;
    }
}
