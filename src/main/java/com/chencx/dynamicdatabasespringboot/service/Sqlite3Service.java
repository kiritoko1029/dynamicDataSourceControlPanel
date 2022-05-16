package com.chencx.dynamicdatabasespringboot.service;

import com.chencx.dynamicdatabasespringboot.model.domain.Sqlmap;

import java.util.List;
import java.util.Map;

public interface Sqlite3Service {
    int addQuerySql(Sqlmap sqlmap);

    List querySqlList();

    Map queryByName(String sqlName);

    int deleteSqlInfo(String sqlName);
}
