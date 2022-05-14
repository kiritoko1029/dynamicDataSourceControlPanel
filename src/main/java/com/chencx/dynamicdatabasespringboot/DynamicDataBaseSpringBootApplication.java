package com.chencx.dynamicdatabasespringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.chencx.dynamicdatabasespringboot.mapper")
@SpringBootApplication
public class DynamicDataBaseSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataBaseSpringBootApplication.class, args);
    }

}
