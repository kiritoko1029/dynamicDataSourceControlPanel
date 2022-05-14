package com.chencx.dynamicdatabasespringboot.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface  APIService {

    List selectAll(String dataBase);
}
