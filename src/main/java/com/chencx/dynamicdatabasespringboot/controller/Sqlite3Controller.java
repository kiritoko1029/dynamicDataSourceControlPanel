package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.model.domain.Sqlmap;
import com.chencx.dynamicdatabasespringboot.service.Sqlite3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * @description  删除sql信息
     * @author chenxiangcai
     * @date 2022/5/16 21:43
     * @param name
     * @return int
     */
    @DeleteMapping("/remove")
    public int remove(String name){
        return  sqlite3Service.deleteSqlInfo(name);
    }

    /**
     * @description  更新sql信息
     * @author chenxiangcai
     * @date 2022/5/21 13:07
     * @param sqlmap
     * @return int
     */
    @PostMapping("/update")
    public int update(@RequestBody Sqlmap sqlmap){
        log.info(String.valueOf(sqlmap));
        return sqlite3Service.updateSqlInfo(sqlmap);
    }
}
