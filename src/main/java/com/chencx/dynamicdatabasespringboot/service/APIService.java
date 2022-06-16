package com.chencx.dynamicdatabasespringboot.service;

import java.util.List;
import java.util.Map;

public interface  APIService {
    List<Map<String,Object>> getList(String sqlName, Map<String,String> args);

}
