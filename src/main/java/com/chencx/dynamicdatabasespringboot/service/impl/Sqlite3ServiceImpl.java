package com.chencx.dynamicdatabasespringboot.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.chencx.dynamicdatabasespringboot.model.domain.Sqlmap;
import com.chencx.dynamicdatabasespringboot.service.Sqlite3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@DS("sqLite")
public class Sqlite3ServiceImpl implements Sqlite3Service {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * @description 添加sql语句到sqlite数据库
     * @author chenxiangcai
     * @date 2022/5/16 10:42
     */
    @Override
    public int addQuerySql(Sqlmap sqlmap) {
        Object[] args = {sqlmap.getName(), sqlmap.getPoolName(), sqlmap.getSqlText(), sqlmap.getArgs()};
        return jdbcTemplate.update("insert into SQLMAP (name ,poolName, sqlText,args) VALUES (?,?,?,?)", args);
    }

    @Override
    public List querySqlList() {
        return jdbcTemplate.queryForList("SELECT * FROM SQLMAP");
    }

    @Override
    public Map queryByName(String sqlName) {
        Object[] args = {sqlName};
        return jdbcTemplate.queryForMap("SELECT * FROM SQLMAP WHERE name=? ", args);
    }

    @Override
    public int deleteSqlInfo(String sqlName) {
        Object[] args = {sqlName};
        return jdbcTemplate.update("DELETE FROM SQLMAP WHERE name =?", args);
    }

}
