package com.chencx.dynamicdatabasespringboot.config;

import com.chencx.dynamicdatabasespringboot.common.MyThreadPoolExecutor;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;
@Slf4j
/**
 * @ClassName ThreadPoolConfig
 * @Description 配置类中构建线程池实例，方便调用
 */
@Configuration
public class ThreadPoolConfig {
    @Value("${logs.path}")
    private String logPath;

    @Bean(value = "threadPoolInstance")
    public ExecutorService createThreadPoolInstance() {
        //通过guava类库的ThreadFactoryBuilder来实现线程工厂类并设置线程名称
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build();
        ExecutorService threadPool = new MyThreadPoolExecutor(1, 5, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        return threadPool;
    }

    @Bean(value = "cmdProcessInstance")
    public Process creatProcessInstance() {
        LocalDate  localDate=LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String cmd = "tail -f "+logPath+date+"/all."+date+".0.log";
        log.info(cmd);
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return process;
    }

}