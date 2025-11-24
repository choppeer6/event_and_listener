package com.siyuan.springevent.event;

import org.springframework.context.ApplicationEvent;

// 自定义事件
public class CustomEvent extends ApplicationEvent {

    private String message;
    private String type;
    public CustomEvent(Object source, String message, String type) {
        super(source);
        this.message = message;
        this.type = type;
    }
    public CustomEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }
    public String getType() {
        return type;
    }
}
