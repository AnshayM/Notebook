# Spring相关问题

#### 1.Spring如何解决循环依赖
[原文参考](https://cloud.tencent.com/developer/article/1769948)

Spring内部有三级缓存：
- singletonObject, 一级缓存，用于保存实例化、注入、初始化完成的bean实例
- earlySingletonObjects, 二级缓存，用于保存实例化完成的bean实例
- singletonFactories, 三级缓存，用于保存bean创建工厂，一遍后面扩展有机会创建代理对象

以TestService1和TestService2为例
![](https://i.bmp.ovh/imgs/2022/05/16/e2b7562846b99295.png)


#### IOC的过程

#### aop
