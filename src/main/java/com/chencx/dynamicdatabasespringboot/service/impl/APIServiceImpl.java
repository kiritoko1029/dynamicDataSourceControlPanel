package com.chencx.dynamicdatabasespringboot.service.impl;


import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.chencx.dynamicdatabasespringboot.service.APIService;
import com.chencx.dynamicdatabasespringboot.service.Sqlite3Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class APIServiceImpl implements APIService {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private Sqlite3Service sqlite3Service;

    /**
     * @param sqlName
     * @return java.util.List
     * @description 根据sqlName获取sql语句进行查询
     * @author chenxiangcai
     * @date 2022/5/16 14:27
     */
    @Override
    public List getList(String sqlName,Map<String,String> args) {
        Map<String, String> sqlMap = sqlite3Service.queryByName(sqlName);
        DynamicDataSourceContextHolder.push(sqlMap.get("poolName"));//手动切换
        String originSql = sqlMap.get("sqlText");
        if(args!=null) {
            for (String key : args.keySet()) {
                originSql = originSql.replace("{" + key + "}", "'" + args.get(key) + "'");
            }
        }
        return jdbcTemplate.queryForList(originSql);
    }

}
