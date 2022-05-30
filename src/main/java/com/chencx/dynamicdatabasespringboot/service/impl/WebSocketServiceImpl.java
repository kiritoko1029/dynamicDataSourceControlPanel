package com.chencx.dynamicdatabasespringboot.service.impl;

import com.chencx.dynamicdatabasespringboot.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WebSocketServiceImpl implements WebSocketService {


    @Resource//消息发送模板
    SimpMessagingTemplate simpMessagingTemplate;

    @Resource(name = "threadPoolInstance")
    private ExecutorService executorService;
    @Resource(name = "cmdProcessInstance")
    private Process process;
    @Value("${logs.path}")
    private String logPath;

    /**
     * @description 不用线程池的方式
     * @author chenxiangcai
     * @date 2022/5/30 11:05
     */
    public void sendMessage() {
        try {
            // 通过Process和Runtime执行linux命令
            LocalDate localDate = LocalDate.now();
            String date = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            String cmd = "tail -f " + logPath + date + "/info." + date + ".0.log";
            Process process = Runtime.getRuntime().exec(cmd);
            //需要另外启动线程进行读取，防止输入流阻塞当前线程
            Thread inputThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.info("创建线程：" + Thread.currentThread().getName());
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(process.getInputStream(), "UTF-8"));
                        String lineOne;
                        while ((lineOne = br.readLine()) != null) {
                            simpMessagingTemplate.convertAndSend("/topic/greeting", lineOne + "\n");
                        }
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            inputThread.start();
            //主线程读取错误输出流数据
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            StringBuffer line = new StringBuffer();
            String lineOne = null;
            int count = 0;
            int lineNum = 1;
            while ((lineOne = br.readLine()) != null) {
                if (count == 100) {
                    simpMessagingTemplate.convertAndSend("/topic/greeting", line.toString() + "\n");
                    line = new StringBuffer();
                    count = 0;
                } else {
                    line.append(lineOne + "\n");
                    count++;
                    lineNum++;
                }
            }

            simpMessagingTemplate.convertAndSend("/topic/greeting", line.toString() + "\n");
            //等待正常输出流线程读取完成后，统一销毁进程
            inputThread.join();

            // 返回码 0 表示正常退出 1表示异常退出
            int extValue = process.waitFor();
            if (0 == extValue) {
                log.info("Exit Success!");
                process.destroy();
                log.info("销毁线程：" + Thread.currentThread().getName());
            } else {
                log.info("Exit failure！");
                process.destroy();
                log.info("销毁线程：" + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute shell fail！", e);
            log.info("Exit failure！");
        }
    }

    /**
     * @description 用线程池的方式
     * @author chenxiangcai
     * @date 2022/5/30 11:05
     */
    @Override
    public void sendMessage2() throws IOException {

        //需要另外启动线程进行读取，防止输入流阻塞当前线程
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("线程：" + Thread.currentThread().getName() + "正在运行...");
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(process.getInputStream(), "UTF-8"));
                    String lineOne;
                    while ((lineOne = br.readLine()) != null) {
                        simpMessagingTemplate.convertAndSend("/topic/getLogs", lineOne + "\n");
                    }
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //主线程读取错误输出流数据
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
        StringBuffer line = new StringBuffer();
        String lineOne = null;
        int count = 0;
        int lineNum = 1;
        while ((lineOne = br.readLine()) != null) {
            if (count == 100) {
                simpMessagingTemplate.convertAndSend("/topic/greeting", line.toString() + "\n");
                line = new StringBuffer();
                count = 0;
            } else {
                line.append(lineOne + "\n");
                count++;
                lineNum++;
            }
        }
        simpMessagingTemplate.convertAndSend("/topic/getLogs", line.toString() + "\n");

        // 返回码 0 表示正常退出 1表示异常退出
        int extValue = 0;
        try {
            extValue = process.waitFor();
            if (0 == extValue) {
                log.info("Exit Success!");
                process.destroy();
                log.info("销毁线程：" + Thread.currentThread().getName());
            } else {
                log.info("Exit failure！");
                process.destroy();
                log.info("销毁线程：" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("execute shell fail！", e);
            log.info("Exit failure！");
        }

    }

    @Override
    public boolean isThreadPoolShutDown() {
        return executorService.isShutdown();
    }

    @Override
    public void threadPoolShutdownNow() {
        executorService.shutdown();
    }
}