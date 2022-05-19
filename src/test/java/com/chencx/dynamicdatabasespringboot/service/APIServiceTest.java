package com.chencx.dynamicdatabasespringboot.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class APIServiceTest {
    @Resource
    private APIService apiService;
    @Test
    void getList() {
        //apiService.getList("testargs");
    }
}