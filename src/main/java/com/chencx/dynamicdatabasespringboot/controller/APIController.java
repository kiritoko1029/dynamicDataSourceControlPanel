package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.service.APIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.devtools.restart.FailureHandler;
import org.springframework.boot.devtools.restart.Restarter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/rest")
public class APIController {
    @Resource
    private APIService apiService;
  /**
   * 适合查询多条数据
   * @author chenxiangcai
   * @date 2022/6/16 10:26
   * @param sqlName
   * @param args
   * @return java.util.List
   */
    @PostMapping("/query/{sqlName}")
    public  List<Map<String,Object>> getList(@PathVariable String sqlName, @RequestBody(required = false) Map<String,String> args) {

        return apiService.getList(sqlName,args);
    }
    @GetMapping("/reboot")
    public int  reboot(){
        Restarter restarter = Restarter.getInstance();
        restarter.restart(failure -> {
            log.error("当前系统出现问题，无法重启项目...........");
            return FailureHandler.Outcome.ABORT;
        });
        return 1;
    }
}
