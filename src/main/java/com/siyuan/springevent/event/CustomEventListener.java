package com.siyuan.springevent.event;


import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component

// 监听器
public class CustomEventListener {

    @EventListener(condition = "#customEvent.type == 'info'") // 条件判断
    @Async // 异步处理
    public void onCustomEvent(CustomEvent customEvent) {
        System.out.println("CustomEventListener msg: " + customEvent.getMessage());
        System.out.println(Thread.currentThread().getName()); // 输出当前线程名称
    }
}
