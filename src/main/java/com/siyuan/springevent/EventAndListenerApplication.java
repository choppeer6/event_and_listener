package com.siyuan.springevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventAndListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventAndListenerApplication.class, args);
        System.out.println("启动成功");

    }

}
