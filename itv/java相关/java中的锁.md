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

#### 3无锁 VS 偏向锁 VS 轻量级锁 VS 重量级锁
以上是synchronized的四种状态，级别从低到高。
![synchronized的四种状态](https://img-blog.csdnimg.cn/2018112210411172.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2F4aWFvYm9nZQ==,size_16,color_FFFFFF,t_70)


这里引入两个概念，java对象头和Moitor

**java对象头**
HotSpot对象头包括MarkWord(标记字段)、Klass Pointer(类型指针)。
Mark Word: 默认存储对象的HashCode、分代年龄和锁标志为信息。
Klass Point: 对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个类是哪个类的实现。

**Monitor**：
	是一个同步工具/同步机制，通常描述为一个对象。每个java对象都有一个内部锁或者Monitor锁。
monitor是线程私有的数据结构，每个线程又一个可用monitor record列表以及一个全局的可用列表。每一个被锁的对象都会和一个monitor关联，同时monitor中又一个Owner记录拥有该锁对应的线程id。

synchronized通过monitor来实现线程同步，monitor依赖底层的操作系统的Mutex Lock(互斥锁)来实现线程同步。
1.6之前，synchronized频繁切换上下文状态，耗时长，就是JDK6之前效率低的原因。这种依赖底层Mutex Lock(互斥锁)实现的锁称为“重量级锁”。JDK6为了减少获得锁和释放锁带来的性能消耗，引入了“偏向锁”和“轻量级锁”



- 无锁：不对资源锁定，都可以访问，但同时只有一个线程能修改成功。其他失败的线程会不断重试到成功为止。

  CAS原理及应用就是无锁的实现。无法全面代替有锁，但在某些场合性能高。

- 偏向锁： 一段同步代码一直被一个线程访问，那么该线程自动获取锁，降低获取锁的代价。
  当一个线程访问同步代码块获取锁时，会在对象头里保存这个线程id。线程进入和退出同步代码块时，不再通过CAS操作来进行加锁和解锁，而是检测Mark Word李是否存储指向当前线程的偏向锁。
  **优点：**偏向锁可以在无多线程竞争的情况下尽量减少不必要的轻量级锁执行操作，轻量级锁的获取和释放依赖多次CAS原子操作，偏向锁只需要在置换ThreadId时依赖一次CAS原子指令即可。

  只有在**其他线程尝试竞争偏向锁时**，持有偏向锁的线程才会释放锁。线程不会主动释放偏向锁。

  偏向锁的释放，需要等待安全点(没有字节码正在执行)，首先暂停拥有偏向锁的线程，判断对象是否处于被锁状态。撤销后，恢复到**无锁(标识位01)**或者**轻量级锁标志位(00)**的状态。

- 轻量级锁：锁是偏向锁时，被其他线程访问，就会升级为轻量级锁。其他线线程会通过自旋的形式尝试获取锁，不会阻塞，从而提升性能。

- 自旋超多指定次数(默认10)时，升级为重量级锁。此时Mark Word中存储存储的事指向重量级锁的指针，此时等待锁的线程都会进入阻塞状态。

总结：
偏向锁通过对比Mark Word解决加锁问题，避免执行CAS操作。
轻量级锁通过CAS操作和自旋来解决枷锁问题，避免线程阻塞和唤醒而影响性能。
重量级锁是将除了拥有锁的线程以外的线程都阻塞。

![synchronized原理](https://i.bmp.ovh/imgs/2022/05/17/4c9d6753d671b1ee.png)




#### 4.公平锁VS非公平锁
公平锁：
多个线程按照申请顺序获取锁，现在直接进入队列中排队。
优点是等待锁的线程不会饿死，缺点是吞吐效率低。开销大。

非公平锁：
多个线程加锁时，不按顺序，直接抢占式获取锁。
优点是减少唤起线程的开销，吞吐高。缺点是等待队列的线程可能会饿死或者等待很久。


公平锁和非公平锁的源码实现区别只在于公平锁在获取同步状态时多路一个限制条件 ```hasQueuedPredecessors()```，用于判断当前线程是否处于队列的的第一个。


#### 5.可重入锁VS非可重入锁
可重入锁又名递归锁，同一线程在外层方法获取锁的时候，在进入该线程的内部方法会自动获取锁。 
Synchronized和ReentrantLock都是可重入锁。优点是可在一定程度上避免死锁。

#### 6.独享锁和共享锁

**独享锁**，又叫排他锁，一次只能被一个线程拥有

**共享锁**，该锁可以被多个线程持有。线程T对数据A加上共享锁后，其他线程只能对A加共享锁，不能加排他锁。可以获取共享锁的线程只能**读数据**，不能修改数据

独享锁和共享锁都是通过AQS来实现的，通过实现不同的方法，来实现独享或者共享。



#### 7死锁

死锁是由于两个线程相互访问对方的资源且相互等待阻塞。

造成死锁的四个条件：

1，互斥原则：一个资源只能被一个线程使用
2，请求与保持：一个线程因请求资源而阻塞时，已经获取的资源保持不放
3，不剥夺条件：线程已经获得的资源，在使用完之前，不能强行剥夺
4，循环等待条件：多个线程之间形成一种头尾相连的循环等待资源关系



#### 8.synchronized和ReenterantLock的区别

synchronized是关键字，ReenterantLock是类。
ReenterantLock可以被继承，可以有方法和变量，提供了更多的接口。

1. ReenterantLock可以对锁的等待时间进行配置，可以避免死锁
2. ReenterantLock可以获得锁的各种信息
3. ReenterantLock可以实现锁的多路通知

底层实现上，ReenterantLock调用的unsafe的park方法加锁。synchronized是通过底层的monitor对象,操作对象头的mark word来进行操作的。