package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.model.domain.Sqlmap;
import com.chencx.dynamicdatabasespringboot.service.Sqlite3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/sqlite")
public class Sqlite3Controller {
    @Resource
    Sqlite3Service sqlite3Service;

    /**
     * @description  添加sql语句到sqlite数据库
     * @author chenxiangcai
     * @date 2022/5/16 10:54
     * @param sqlmap
     * @return int
     */
    @PostMapping("/add")
    public int addQuerySql(@RequestBody Sqlmap sqlmap){
      return sqlite3Service.addQuerySql(sqlmap);
    }

    /**
     * @description  获得添加的所有sql信息
     * @author chenxiangcai
     * @date 2022/5/16 10:55
     * @return java.util.List
     */
    @GetMapping("/getList")
    public List getByList(){
        return sqlite3Service.querySqlList();
    }
    @DeleteMapping("/remove")
    public int remove(String name){
        return  sqlite3Service.deleteSqlInfo(name);
    }
}