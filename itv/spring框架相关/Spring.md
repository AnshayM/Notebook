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



#### spring配置bean的方式

- 基于xml
- 基于注解
- 基于java类



##### spring的作用域

singleton、prototype、request、session、application

**singleton** ：单例类型
**prototype**：一个bean定义对应多个对象实例
（request、session、application都是在web环境下使用的。）
**request**：是请求作用域，每次接收请求时创建一个bean实例
**session**：会话作用域，服务器和浏览器的一次会话过程，连续的一个状态而非一个请求。sesion结束后销毁
**application**：全局作用域，bean是serveletContext级别的。单例作用于某一个application容器中，但一个项目不仅仅只有以恶搞applicaitonContext。

spring有两个核心接口：BeanFactory和ApplicaitonContext，其中ApplicaitonContext是BenaFactory的字接口，他们都可以代替Srping容器，spring