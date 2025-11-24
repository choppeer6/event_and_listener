# Spring Event Demo

这是一个基于Spring Boot的事件驱动架构示例项目，演示了如何使用Spring的事件机制来实现应用程序内部的解耦通信。

## 项目概述

本项目展示了Spring Framework中的事件发布-订阅模式，包括：
- 自定义事件的创建和发布
- 事件监听器的实现
- 不同类型事件的处理机制

## 技术栈

- Java 17+
- Spring Boot 4.0.0
- Spring Framework 7.0.1

## 核心组件

### 1. 自定义事件 (CustomEvent)
[CustomEvent](file://D:\Program%20Files\github\Java_demo\event_and_listener\src\main\java\com\siyuan\springevent\event\CustomEvent.java#L5-L24) 类继承自 `ApplicationEvent`，包含消息内容和事件类型等属性。

### 2. 事件发布服务 (EventPublisherService)
负责创建和发布自定义事件到Spring的应用上下文。

### 3. 事件监听器
通过 `@EventListener` 注解实现对特定事件的监听和处理。

## 高级特性

### 条件事件监听 (Conditional Event Listening)

使用 `@EventListener` 的 `condition` 属性可以基于SpEL表达式实现条件过滤：

```java
// 只处理类型为 'info' 的事件
@EventListener(condition = "#event.type == 'info'")
public void handleInfoEvents(CustomEvent event) {
    System.out.println("处理信息类事件: " + event.getMessage());
}

// 只处理消息长度大于10的事件
@EventListener(condition = "#event.message.length() > 10")
public void handleMessageLengthEvents(CustomEvent event) {
    System.out.println("处理长消息事件: " + event.getMessage());
}

// 基于多个条件的组合判断
@EventListener(condition = "#event.type == 'error' and #event.source != null")
public void handleErrorEvents(CustomEvent event) {
    System.out.println("处理错误事件: " + event.getMessage());
}
```


### 异步事件处理 (Asynchronous Event Processing)

通过 `@Async` 注解实现事件监听器的异步执行：

```java
// 异步处理事件，不阻塞发布线程
@EventListener
@Async
public void handleAsyncEvent(CustomEvent event) {
    System.out.println("异步处理事件: " + event.getMessage() + 
                      " 线程: " + Thread.currentThread().getName());
}

// 结合条件判断的异步处理
@EventListener(condition = "#event.type == 'async'")
@Async
public void handleAsyncConditionalEvent(CustomEvent event) {
    // 模拟耗时操作
    try {
        Thread.sleep(2000);
        System.out.println("异步处理完成: " + event.getMessage());
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
```


启用异步支持需要在配置类上添加 `@EnableAsync` 注解：

```java
@SpringBootApplication
@EnableAsync
public class EventAndListenerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventAndListenerApplication.class, args);
    }
}
```


## 运行项目

```bash
# 克隆项目
git clone <repository-url>

# 进入项目目录
cd spring-event-demo

# 运行项目
./mvnw spring-boot:run
```


## 主要特性

1. **事件驱动架构**: 使用Spring的ApplicationEvent机制实现松耦合
2. **自动事件发布**: 应用启动完成后自动发布示例事件
3. **灵活的事件监听**: 支持基于注解的事件监听器配置
4. **条件过滤**: 通过SpEL表达式实现精确的事件过滤
5. **异步处理**: 支持非阻塞的事件处理机制

## 使用说明

项目启动后会自动触发事件发布流程，相关日志会输出到控制台，展示事件的发布和处理过程。

## 学习价值

- 理解Spring事件机制的工作原理
- 掌握事件驱动架构的设计模式
- 学习如何在实际项目中应用事件解耦
- 掌握条件事件监听和异步处理的高级用法

## 注意事项

- 确保Java版本兼容性
- 注意避免事件监听器中的循环调用问题
- 合理设计事件类型和监听器条件表达式

## 事件驱动机制和MQ驱动机制对比
MQ驱动的作用：解耦、异步、削峰，就不过多解释了。

        优点：MQ可供并发量大、微服务使用

        缺点：使用MQ会提升架构的复杂度，维护性降低。

事件驱动机制：解耦、异步，做不到削峰。

        优点：spring框架自带易维护，集成简单

        缺点：无法支撑大并发，只能单机通知，无法排队，无法削峰。

总结：消息量不大时就可以使用事件驱动机制
- 异步处理时注意线程安全和异常处理
