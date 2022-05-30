package com.chencx.dynamicdatabasespringboot.service;

import java.io.IOException;

public interface WebSocketService {
    void sendMessage();
    void sendMessage2() throws IOException;

    boolean isThreadPoolShutDown();

    void threadPoolShutdownNow();
}
