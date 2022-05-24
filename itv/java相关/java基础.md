#### java8的新特性

- lambda表达式

    允许把函数作为一个方法的参数

- Stream API，流式编程

- Date Time API -加强对日期和时间的处理-**本地**和**时区**

    原有Date类有很多问题，非线程安全、设计感很差、时区处理麻烦，未提供国际化
    java8后在java.time中提供了比较重要的两个API，

    Local本地。简化了日期时间的处理。
    Zoned(时区)-指定的时区处理日期时间。

- Optional类-成为java8类库的一部分，用来解决空指针异常
    多在stream的筛选结果中使用

#### 数组和链表的区别

ArrayList和LinkedList
1 内存分配-连续有限的空间
2 增加变量时，扩容方式
	arrayList半倍增加，并且需要开辟一块新的内存空间，将数据复制过去。

#### HashMap

主要讲HashMap的调整，并且详细说明HashMap的实现原理
ConcurrentHashMap的更新