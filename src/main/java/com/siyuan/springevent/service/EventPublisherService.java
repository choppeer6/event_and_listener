package com.siyuan.springevent.service;

import com.siyuan.springevent.event.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

//发布者
@Service
public class EventPublisherService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    // 监听ApplicationReadyEvent事件 项目启动时运行这块代码
    @EventListener(ApplicationReadyEvent.class)
    public void publicEnvent() {

        CustomEvent customEvent = new CustomEvent(this, "hello world", "info");
        System.out.println(Thread.currentThread().getName());
        applicationEventPublisher.publishEvent(customEvent);
    }
}
