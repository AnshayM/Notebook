# Spring相关问题

[参考地址](https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/spring/spring-knowledge-and-questions-summary?id=%e4%bb%80%e4%b9%88%e6%98%af-spring-%e6%a1%86%e6%9e%b6)

#### 说一下你对于spring的了解

Spring是一款轻量级的开发框架，旨在提高开发人员的开发效率以及可维护性。
同时它内部集成了很多其他框架，可以说是框架的框架。

![内部模块](https://guide-blog-images.oss-cn-shenzhen.aliyuncs.com/github/javaguide/jvme0c60b4606711fc4a0b6faf03230247a.png)





#### Spring如何解决循环依赖

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

Aspect-Oriented Programming: 面向切面编程
将一些与业务无关、但为业务模块所共同调用的逻辑或者责任(**事务处理**、**日志管理**、**权限控制等**)封装起来，**减少系统的重复代码，降低模块质检的耦合度**，有利于未来的可拓展性和可维护性。

**springAop是基于动态代理的**，代理的对象实现了某个接口，spring Aop会使用JDK proxy去创建代理对象。没有实现接口的对象，无法通过JDK的Proxy进行代理，这时候Spring AOP会使用Cglib生成一个被代理对象的子类作为代理。
![](https://images.xiaozhuanlan.com/photo/2019/926dfc549b06d280a37397f9fd49bf9d.jpg)

注：同一个类的内部调用，需要显式的调用代理对象才可以实现切面。比如service内部调用自己的方法时，可以通过context拿到当前bean再调用或者将自己@Autowired进来进行this.xxxx()调用。

用到了哪些注解：
@Aspect: 声明当前类为切面类
@Before：前置通知。属性value：用于指定切入点表达式引用
@AfterReturning：后置通知，value：指定切入点的表达式或引用
@AfterThrowing：异常通知，value：...
@After：始终通知，value：
@Around: 环绕通知，value：
@Pointcut：指定切入表达式，value：表达式内容

#### spring配置bean的方式

- 基于xml
- 基于注解
- 基于java类



#### Spring bean的5个作用域

**singleton** ：唯一的bean实例，spring中的bean默认都是单例的
**prototype**：原型模式，每次通过 Spring 容器获取 prototype 定义的 Bean 时，容器都将创建一个新的 Bean 实例。
（request、session、application都是在web环境下使用的。）
**request**：请求作用域，每次接收请求时创建一个bean实例
**session**：会话作用域，服务器和浏览器的一次会话过程，连续的一个状态而非一个请求。sesion结束后销毁
**application**(global-session)：全局作用域，bean是serveletContext级别的。单例作用于某一个application容器中，但一个项目不仅仅只有一个applicaitonContext。

spring有两个核心接口：BeanFactory和ApplicaitonContext，其中ApplicaitonContext是BenaFactory的子接口，他们都可以代替Srping容器。

#### Spring bean中的单例bean的线程安全问题了解吗

当多个线程操作同一个对象是，对象的非静态成员变量的写操作会存在线程安全问题，解决方式如下：

1. 在Bean中尽量避免定义可变的成员变量
2. 在类中定一个一个ThreadLocal成员变量，将需要的可变成员变量保存在ThreadLocal中。

#### Bean的生命周期

![bean的生命周期](https://images.xiaozhuanlan.com/photo/2019/b5d264565657a5395c2781081a7483e1.jpg)

1. 实例化对象：Bean容器找到配置文件中springbean的定义，Bean容器利用Java reflection Api创建一个Bean的实例
2. 设置属性：如果涉及到一些属性值，利用set方法设置对应的属性。
3. 检查各种**aware**接口，根据对应set方法设置依赖：BeanNameAware、BeanClassLoader、BeanFactory接口
4. 前置处理：如果有和加载这个Bean的Spring容器相关的**BeanPostProcessor**对象，执行**postProcessBeforeInitialization()**方法
5. 判断是否InitializingBean：是则执行afterPropertiesSet()方法
6. 判断init-method：如果有配置init-method属性，执行指定的方法
7. 后置处理：如果有和加载这个Bean的Spring容器相关的**BeanPostProcessor**对象，执行**postProcessAfterInitialization()**方法
8. 注册DesTruction回调接口
9. 判断是否DisposableBean：如果是，当销毁Bean的时候，执行destroy()方法
10. 判断自定义destroy-method：如果有，销毁Bean的时候，执行指定的方法

为什么要做前置处理和后置处理

#### Spring框架中用到什么设计模式

1. 工程模式：Spring使用工厂模式通过BeanFactory、ApplicationContext创建bean对象
2. 代理模式：spring aop功能实现
3. 单例模式：spring bean默认都是单例的。其中有饱汉模式和恶汉模式。
4. 观察者模式：Spring事件驱动模型就是观察者模式的很经典的应用
5. 适配器模式：SpringAop的增强或者通知使用到了适配器模式，Spring mvc也是用适配器模式适配Controller



#### Spring事务实现的原理

1. 解析各个方法事务相关的属性，根据具体的属性来判断是否开启新事物
2. 需要开启的时候，获取数据库连接，关闭自动提交功能，开启事务
3. 执行具体的sql操作
4. 操作过程中，执行失败，通过completeTranscationAfterThrowing来完成事务的回滚操作。具体回滚逻辑是通过doRollBack来实现的。实现也需要先获取操作对象，通过连接对象来回滚。
5. 操作过程正常执行完，则通过completeTranscationAfterReturning来完成事务的提交操作。提交的具体逻辑是通过doCommit方法实现的。实现是也需要获取连接，通过连接对象提交。

（todo：后面需要详细了解清楚）事务传递的实现，是通过ThreadLocal实现。
创建玩transcation对象后，会判断是否已经存在一个事务，如果有就会调用handleExistingTranscation方法，根据配置的事物传递策略进行判断是否要加进去。



#### Spring事务的隔离等级

TransactionDefinition接口定义了5种隔离等级，和mysql数据库的4个隔离等级一致，多路一个是采用数据库默认隔离等级

- TransactionDefinition.ISOLATION_DEFAULT: 使⽤后端数据库默认的隔离级别，Mysql 默认采 
    ⽤的 REPEATABLE_READ隔离级别 Oracle 默认采⽤的 READ_COMMITTED隔离级别. 
- TransactionDefinition.ISOLATION_READ_UNCOMMITTED: 最低的隔离级别，允许读取尚未提交的 
    数据变更，可能会导致脏读、幻读或不可重复读
- TransactionDefinition.ISOLATION_READ_COMMITTED: 允许读取并发事务已经提交的数据，可以 
    阻⽌脏读，但是幻读或不可重复读仍有可能发⽣
- TransactionDefinition.ISOLATION_REPEATABLE_READ: 对同⼀字段的多次读取结果都是⼀致 
    的，除⾮数据是被本身事务⾃⼰所修改，可以阻⽌脏读和不可重复读，但幻读仍有可能发⽣。 
- TransactionDefinition.ISOLATION_SERIALIZABLE: 最⾼的隔离级别，完全服从ACID的隔离级 
    别。所有的事务依次逐个执⾏，这样事务之间就完全不可能产⽣⼲扰，也就是说，该级别可以防 
    ⽌脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会⽤到该级别。

#### spring事务传播机制

支持当前事务的情况：共用同一个事务

- TransactionDefinition.PROPAGATION_REQUIRED(按需): 如果存在，就加入该事务。如果没有，就创建
- TransactionDefinition.PROPAGATION_SUPPORTS(支持): 如果存在，则加入该事务。如果没有，则以非事务方式运行。
- TransactionDefinition.PROPAGATION_MANDATORY(强制): 如果存在，则加入该事务。如果没有，则抛出异常。

不支持当前事务的情况：不使用同一个事务

- TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新事务，如果当前存在事务，就把当前事务挂起。外层报错，不影响内存的正常提交。内层报错，外层也会回滚。
- TransactionDefinition.PROPAGATION_NOT_SUPPORT：以非事务运行，如果当前存在事务，则把当前事务挂起。
- TransactionDefinition.PROPAGATION_NEVER：以非事务运行，如果存在，则抛出异常

其他情况

TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务运行；如果当前没有事务，则取值等价于TransactionDefinition.PROPAGATION_REQUIRED，即创建一个。外层事务异常，内层也会回滚。



####      @Transactional注解

可以作用于方法和类

如果不配置rollbackFor属性，事务只会遇到RuntimeException时才会回滚，加上rollbackFor=Exception.class可以让事务在遇到非运行时异常也回滚。

![](https://atts.w3cschool.cn/attachments/image/20210802/1627886982927185.png)



todo 事务处理的底层流程，即在抛出异常后怎么记录状态的传递需要弄明白