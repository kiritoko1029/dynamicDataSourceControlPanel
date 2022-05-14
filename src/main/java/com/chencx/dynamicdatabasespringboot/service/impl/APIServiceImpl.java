package com.chencx.dynamicdatabasespringboot.service.impl;


import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.chencx.dynamicdatabasespringboot.service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class APIServiceImpl implements APIService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List selectAll(String dataBase) {

        DynamicDataSourceContextHolder.push(dataBase);//手动切换
        return  jdbcTemplate.queryForList("select * from TGSG.T_LINE_NAME");
    }
}
