package com.chencx.dynamicdatabasespringboot.controller;

import com.chencx.dynamicdatabasespringboot.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;

@Slf4j
@Controller
@RequestMapping(value = "/websocket")
public class WebSocketController {
    @Resource
    private WebSocketService webSocketService;

    @Resource//消息发送模板
    SimpMessagingTemplate simpMessagingTemplate;
    @Value("${logs.path}")
    private static String logPath;

    @GetMapping("/getLogs")
    @ResponseBody
    public String  greeting() throws Exception {
        webSocketService.sendMessage2();
        return "success";
    }
    public static Process createCMDProcess() throws IOException {
        // 通过Process和Runtime执行linux命令
        LocalDate localDate = LocalDate.now();
        String date = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String cmd = "tail -f " + logPath + date + "/info." + date + ".0.log";
        Process process = Runtime.getRuntime().exec(cmd);
        return process;
    }
}
