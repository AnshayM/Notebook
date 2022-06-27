#### 运行时区域

- **堆**(线程共享)：存放对象实例，是GC的主要区域
- **方法区**(线程共享)：存储虚拟机加载的类信息、常量、静态变量等。
    - 运行时常量池：保存所有字符串
- **虚拟机栈**：每个方法会分配一个栈帧，保存对应的局部变量表。每个方从调用到完成对应栈帧在虚拟机栈的入栈到出栈的过程。
- **本地方法栈**：虚拟机栈为字节码服务，而本地方法栈为虚拟机使用的Native方法服务
- **程序计数器**：当前线程执行的字节码的行号指示器。用于在多线程中记录当前运行的位置。

在1.8以后，方法区被改为元空间



#### 怎么判断一个对象是否可以回收

引用计数法

**可达性分析法**

通过一系列的GCRoots对象作为起点。可作为GCRoots的：

1. 虚拟机栈中引用的对象
3. 本地方法栈中JNI(Native方法)引用的对象
3. 方法区中静态属性和常量引用的对象

引用也分，强软弱虚引用，这一块不用太过深入。






#### 聊聊垃圾回收的流程

![垃圾回收主要区域](https://snailclimb.gitee.io/javaguide/docs/java/jvm/pictures/jvm%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6/01d330d8-2710-4fad-a91c-7bbbfaaefc0e.png)

#### 垃圾回收算法
**标记清除**：标记要回收的对象，然后后原地处理，产生大量内存碎片

**复制**：2块相等的，将存活的复制到另一块中，清除原来的内存块。例如Survivor的from和to。

**标记整理**：标记后，然后所有存活的对象往一端挪动。

**分代收集**：根据对象存活周期将内存划分好几块，在新生代用复制，在老年代用清除或标记整理。



#### 垃圾回收器

![垃圾回收期](https://upload-images.jianshu.io/upload_images/16289066-976546a51d6c694b.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/600/format/webp)


新生代收集器：

- Serial
- ParNew
- parallel

老年代：

- Serial Old
- CMS
- Parallel Old

新生代和老年代
G1



#### GC调优

<img src="https://i.bmp.ovh/imgs/2022/05/23/585422bed89c9002.png" alt="调优步骤" style="zoom:50%;" />

#### cpu飙高怎么处理

1. top命令查看各个进程的cpu使用情况
2. ps，查看进程cpu使用情况
3. jstack，java提供的命令查看当前线程栈运行状态、运行代码，以及是否死锁
4. pstack，linux查看某个进程的当前线程栈运行情况

负载：需要运行处理但又必须等待队列前的进程处理完成进程个数



#### 内存溢出怎么排查

1. 使用top -p pid针对要查的pid查询该进程的内存以及负载情况
2. Map -histo:live[pid],分析具体的对象数目和占用内存大小，从而定位代码
3. jmap -dump:live,format=b,file=xxx.xxx[pid],dump下对应的堆日志，使用mat工具分析是否存在内存泄漏等

todo 常用的jstack命令



#### 类加载机制

加载 - 链接(验证-准备-解析) - 初始化 - 使用 - 卸载

加载：**在内存中生成一个代表这个类的java.lang.class对象，作为方法区这个类的各种数据的入口**。

验证：确保Class文件的字节流中包含的信息是否符合当前虚拟机的要求。

准备：在方法区中分配这些变量所使用的内存空间。

![](https://pic4.zhimg.com/80/v2-5473646d79609214433ee7a66e594603_1440w.png)



[原文](https://zhuanlan.zhihu.com/p/25228545)



#### 调优过程：

##### 通过jstat命令查看gc状态
查看gc状态 jstat -gc pid

```
[lhdop@blog ~]$ jstat -gc 20756 
S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT    CGC    CGCT     GCT
0.0   6144.0  0.0   4712.7 73728.0    0.0     57344.0    34393.5   61132.0 59888.7 7372.0 6980.1     19    0.172   0      0.000   6      0.019    0.191
```

S0C: survivor 0区域的容量，以KB为单位
S1C: survivor 1区域的容量，以KB为单位
S0U: survivor 0区域的使用大小，以KB为单位
S1U: survivor 1区域的使用大小，以KB为单位
EC: Eden区域的容量，以KB为单位
EU: Eden区域的使用，以KB为单位
MC: Metaspace元数据区的 Committed Size，以KB为单位
MU: Metaspace元数据区的使用大小，以KB为单位
CCSC: Compressed class的Committed Size，以KB为单位
CCSU: Compressed class的使用大小，以KB为单位
OC: old区域的容量，以KB为单位
OU: old区域的使用，以KB为单位
YGC: young generation(年轻代)的GC次数
YGCT: young generation(年轻代)的GC消耗时间
FGC: full GC的次数
FGCT: full GC的消耗时间
CGC: 并发GC次数(G1 gc)
CGCT: 并发GC的消耗时间(G1 gc)
GCT: GC总消耗时间



查看最近一次gc原因，jstat -gccause pid

```
[lhdop@blog ~]$ jstat -gccause 20756
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT    CGC    CGCT     GCT    LGCC                 GCC
  0.00  76.70  52.78  59.98  97.97  94.68     19    0.172     0    0.000     6    0.019    0.191 G1 Evacuation Pause  No GC
```

S0: survivor 0区域的使用比例
S1: survivor 1区域的使用比例
E: Eden区域的使用比例
O: Old区域的使用比例
M: 元数据区域的使用比例
CCS: Compressed class空间的使用比例
YGC: young generation(年轻代)的GC次数
YGCT: young generation(年轻代)的GC消耗时间
FGC: full GC的次数
FGCT: full GC的消耗时间
CGC: 并发GC次数(G1 gc)
CGCT: 并发GC的消耗时间(G1 gc)
GCT: GC总消耗时间
LGCC: 上次GC的原因
GCC: 当前GC的原因





#### dump堆信息使用mat工具分析