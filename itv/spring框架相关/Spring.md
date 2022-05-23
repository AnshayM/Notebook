# Spring相关问题

[参考地址](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/spring-knowledge-and-questions-summary?id=%e4%bb%80%e4%b9%88%e6%98%af-spring-%e6%a1%86%e6%9e%b6)

#### 说一下你对于spring的了解

Spring是一款轻量级的开发框架，旨在提高开发人员的开发效率以及可维护性。
同时它内部集成了很多其他框架，可以说是框架的框架。



![内部模块](https://guide-blog-images.oss-cn-shenzhen.aliyuncs.com/github/javaguide/jvme0c60b4606711fc4a0b6faf03230247a.png)





#### 1.Spring如何解决循环依赖

[原文参考](https://cloud.tencent.com/developer/article/1769948)

Spring内部有三级缓存：
- singletonObjects, 一级缓存，用于保存实例化、注入、初始化完成的bean实例
- earlySingletonObjects, 二级缓存，用于保存实例化完成的bean实例
- singletonFactories, 三级缓存，用于保存bean的创建工厂，以便后面扩展有机会创建代理对象

以TestService1和TestService2为例
![](https://i.bmp.ovh/imgs/2022/05/16/e2b7562846b99295.png)

#### IOC

是一种设计细想，将原本在程序中手动创建对象的控制权，交由spring容器来管理。


#### Aop

Aspect-Oriented Programming:面向切面编程
将一些与业务无关、但为业务模块所共同调用的逻辑或者责任(事务处理、日志管理、权限控制等)封装起来，减少系统的重复代码，降低模块质检的耦合度，有利于未来的可拓展性和可维护性。

springAop是基于动态代理的，代理的对象实现了某个接口，spring Aop会使用JDK proxy去创建代理对象。

注：同一个类的内部调用，需要显式的调用代理对象才可以实现切面。比如service内部调用自己的方法时，可以通过context拿到当前bean再调用或者将自己@Autowired进来进行this.xxxx()调用。



#### spring配置bean的方式

- 基于xml
- 基于注解
- 基于java类



##### spring的5个作用域

**singleton** ：单例类型
**prototype**：一个bean定义对应多个对象实例
（request、session、application都是在web环境下使用的。）
**request**：是请求作用域，每次接收请求时创建一个bean实例
**session**：会话作用域，服务器和浏览器的一次会话过程，连续的一个状态而非一个请求。sesion结束后销毁
**application**(global-session)：全局作用域，bean是serveletContext级别的。单例作用于某一个application容器中，但一个项目不仅仅只有一个applicaitonContext。

spring有两个核心接口：BeanFactory和ApplicaitonContext，其中ApplicaitonContext是BenaFactory的子接口，他们都可以代替Srping容器。



Bean的生命周期

![bean的生命周期](https://images.xiaozhuanlan.com/photo/2019/b5d264565657a5395c2781081a7483e1.jpg)

1. Bean容器找到配置文件中springbean的定义
2. Bean容器利用Java reflection Api创建一个Bean的实例
3. 如果涉及到一些属性值，利用set方法设置对应的属性，各种**aware**接口，BeanNameAware、BeanClassLoader、BeanFactory接口
4. 如果有和加载这个Bean的Spring容器相关的**BeanPostProcessor**对象，执行**postProcessBeforeInitialization()**方法
5. 如果实现了InitializingBean接口，执行afterPropertiesSet()方法
6. 如果Bean在配置文件中的定义包含init-method属性，执行指定的方法
7. 如果有和加载这个Bean的Spring容器相关的**BeanPostProcessor**对象，执行**postProcessAfterInitialization()**方法
8. 当销毁Bean的时候，如果实现了DisposableBean接口，执行destroy()方法
9. 销毁Bean的时候，如果Bean配置了destroy-method属性，执行指定的方法