# java中各种锁的介绍


[原文](https://www.cnblogs.com/jyroy/p/11365935.html)


![锁的分类](https://img-blog.csdnimg.cn/20181122101753671.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2F4aWFvYm9nZQ==,size_16,color_FFFFFF,t_70)

#### 1.乐观锁和悲观锁
乐观锁：读多写少，每次都会获取一个版本号，需要更新资源时，通过CAS比对当前版本号和之前获取的版本号，未改变执行更新，已改变则报错/重试
被关锁：写多读少，每次都会去获取锁，执行完后释放。synchronized关键字和Lock的实现类都是悲观。

![](https://img-blog.csdnimg.cn/20181122101819836.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2F4aWFvYm9nZQ==,size_16,color_FFFFFF,t_70)

#### 2自旋锁
CPU资源在不同线程中转换需要修改上下文状态。这个操作时比较消耗时间的。

自旋锁就是在多核CPU中，不释放CPU时间片，进行有限制次数的自旋去尝试获取锁，以减少CPU切换以及恢复线程状态的消耗。

内部实现也是CAS，AtomicInteger中进行自增操作的源码中do-while就是自旋操作，修改失败则通过循环来执行。

![](https://img-blog.csdnimg.cn/2018112210212894.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2F4aWFvYm9nZQ==,size_16,color_FFFFFF,t_70)

#### 无锁 VS 偏向锁 VS 轻量级锁 VS 重量级锁
以上时synchronized的四种状态。

![synchronized的四种状态](https://img-blog.csdnimg.cn/2018112210411172.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2F4aWFvYm9nZQ==,size_16,color_FFFFFF,t_70)

![img.png](img.png)