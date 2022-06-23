# RabbitMq



使用Erlang语言编写，最早为电信行业系统之间的可靠通信设计的，少数几个支持AMQP协议的消息队列之一。

**优点：**
1.轻量级、迅速。开箱即用。

```xml
<!--引入依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

```java
// 接收示例：
@RabbitListener(queues = "${queues}",containerFactory = "customContainerFactory", autoStartup = "true")
public void processMessage(Object msg) {
    //todo 
}
```

2.RabbitMQ在生产者和队列之间增加了一个Exchange(交换机)模块。
Exchange：根据配置爹路由规则将生产者发出的消息分发到不同的队列中。

3.支持编程语言多，冷门语言也可以找到对应的客户端。

**问题：**

1. 对消息堆积的支持不好。
    消息队列是一个大管道，大量消息积压是不正常的情况，这种情况发生会导致RabbitMq的性能急剧下降。

2. 性能最差
    官方测试数据是根据配置不同，每秒可以处理几万到几十万数据。
3. Erlang语言的学习曲线比较陡峭。



#### 消息模型

使用队列模型，生产者将消息发送给Exchange，由Exchange上配置的策略来决定将消息投递到哪些队列。

![](http://learn.lianglianglee.com/%E4%B8%93%E6%A0%8F/%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97%E9%AB%98%E6%89%8B%E8%AF%BE/assets/2df04ce80ff54702240df8598f277ca5.jpg)