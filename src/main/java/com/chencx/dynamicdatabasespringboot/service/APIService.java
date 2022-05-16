package com.chencx.dynamicdatabasespringboot.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface  APIService {

    List getList(String sqlName);

    Map getMap(String sqlName);


}
