## 一、Java 基础
#### 1.JDK 和 JRE 有什么区别？
**java development kit**：java开发工具包，包括了jre、编译java源码的编译器javac、java程序调试和分析的如jconsole、jvisualvm等软件工具，还包含了java程序编写所需的文档和demo例子。
**java Runtime  environment** ：包含java虚拟机、java基础类库。是使用java语言编写的程序所需要的软件环境，用于运行java程序。

#### 2. equals 和 == 的区别是什么？
java中的数据类型，可分为两类： 
- 基本数据类型，byte,short,char,int,long,float,double,boolean。只有==方法，没有equals方法，==比较的是他们的值。
- 类类型  
    1. == 比较的是内存地址，除同一个new出来的对象比较结果为true外，其他都为false。 
    2. Object中定义了equals方法——>
```java 
public boolean equals(Object obj) {    
    return (this == obj);
}
```
  其核心也是调用==方法进行比较。但在一些类库，如String,Integer,Date等类当中equals有其自身的实现，覆盖后进行了内容的比较，而不再是比较类在堆内存中的存放地址。equals方法本身设计出来就是为了让人去复写的。
 
为了方便理解，下面给部分源码范例和简单调用。
*String中equals实现*  
```java
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String)anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                        return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```
*String调用*
```java
    String s11 = "12";
    String s12="3";
    String s1=s11+12;

    String s2 = new String("123");
    if(s1==s1) {
        System.out.println("==："+true);
    }else {
        System.out.println("==："+false);
    }

    if(s1.equals(s1)) {
        System.out.println("equals："+true);
    }else {
        System.out.println("equals："+false);
    }
//运行结果
//==：true
//equals：true
```
  
*List中的equals实现* 
```java
public boolean equals(Object o) {
    if (o == this){
        return true;
    } 
    if (!(o instanceof List)){
        return false;
    }
    ListIterator<E> e1 = listIterator();
    ListIterator<?> e2 = ((List<?>) o).listIterator();
    while (e1.hasNext() && e2.hasNext()) {
        E o1 = e1.next();
        Object o2 = e2.next();
        if (!(o1==null ? o2==null : o1.equals(o2))){
            return false;
        }
    }
        return !(e1.hasNext() || e2.hasNext());
}
```
List调用
```java
    List a = new ArrayList<>();
    a.add(1);
    List b = new ArrayList<>();
    b.add(1);
    if(a==b) {
        System.out.println("==："+true);
    }else {
        System.out.println("==："+false);
    }
// 运行结果
// ==：false
// equals：true
```
*Date中equals*
```java
public boolean equals(Object obj) {
        return obj instanceof Date && getTime() == ((Date) obj).getTime();
    }
```
*Date调用*
```java
    //a和b时间差极小，编译器会认为是同一个时间
    Date a = new Date();
    Date b = new Date();
    if(a==b) {
        System.out.println("==："+true);
    }else {
        System.out.println("==："+false);
    }

    if(a.equals(b)) {
        System.out.println("equals："+true);
    }else {
        System.out.println("equals："+false);
    }
//运行结果
//==：false
//equals：true
```


#### 3.两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？
不对。
equals方法默认是比较两者的内存地址，封装类复写后为内容比较。
hashCode方法给对象返回一个hashCode值，用于hashtables，如HashMap。
性质;
- 在一个java应用的执行期间,如果一个对象提供给equls作比较的信息没被修改，则该对象多次调用hashCode方法始终放回同一个integer。
- 如果两个对象根据equsl方法比较是相等的，那么调用二者各自的hashCode方法必须产生同一个integer结果。
- 并不要求根据equals方法不相等的两个对象，调用二者各自的hashCode方法必须产生不同integer结果。然而，程序员应该意识到对于不同的对象产生不同的integer结果，有助于提高hash table的性能。

假如两个Java对象A和B，A和B不相等（eqauls结果为false），但**A和B的哈希码相等，将A和B都存入HashMap时会发生哈希冲突**，也就是A和B存放在HashMap内部数组的位置索引相同。这时HashMap会在该位置建立一个链接表，将A和B串起来放在该位置，显然，该情况不违反HashMap的使用原则，是允许的。当然，哈希冲突越少越好，尽量采用好的哈希算法以避免哈希冲突。

部分细节可以参考[equals()与hashCode()方法详解](https://www.cnblogs.com/Qian123/p/5703507.html)。
- 这里考察equals和hashcode方法规范，以及hashmap实现原理。如果有多余时间，需要看看HashMap源码，理解其实现原理。

#### 4.final 在 java 中有什么作用？
*变体：说出java中final、finaly、finalize 的区别。*
- final:修饰变量时表示不可被改变（如果变量指向一个值的引用，那么变量是可以更改的，但是被引用的值不可更改），修饰方法时表示不可被复写，修饰类时表示不可被继承。
- finally：在try-catch代码块中，不管tyr块中的异常是否抛出，finally中的代码都能得到执行。主要用于清理try语句块中使用的资源。扩展：对于无垃圾回收和析构函数自动调用机制的语言来说，无论try语句块里发了什么，内存总能得到释放。
- finalize：finalize()用于判断对象是否可被回收。在进行垃圾回收时，对象需要至少进行两次标记过程。第一次标记的筛选依据即是否有必要执行finalize方法。对象没有覆盖或者已经被调用过，则视为没有必要执行。如果有必要执行，这个对象会被仿佛在一个叫做F-Queue的队列中。然后稍后油一个有虚拟机自动i建瓯i的、低优先级的Finalizer线程去执行他。但是在Finaliz()方法中，对象也可以实现自救，把this关键字赋值给某个类变量或者对象成员变量，即在方法体中重新被引用移出“即将回收”的集合。但是此方法不推荐使用。

#### 5.java 中的 Math.round(-1.5) 等于多少？
该方法返回最接近或int/long整型，返回给定方法的参数类型。相当于四舍五入。
考察常用的Math类方法。


#### 6.String 属于基础的数据类型吗？
java中8个基础数据类型：int、byte、short、long、boolean、char、double、float。
String 是java lang包下的类，是引用数据类型，定义语句如下
public final class String extends Object implements Serializable, Comparable<String>, CharSequence
继承于Object，实现Serializable、Comparable、CharSequence接口。

扩展：基本数据类型存储在栈上，引用类型存储在堆上。基本数据类型都有自己对应的封装类，能够自动拆箱装箱，但是基本数据类型有默认值，封装类型没有。命名上，基本数据类型都是小写，封装类型为：int-Integer、byte-Byte、short-Short、long-Long、boolean-Boolean、char-Character、double-Doubler、float-Float。

#### 7.java 中操作字符串都有哪些类？它们之间有什么区别？
String(字符串常量)、
StringBuilder(字符串变量，线程安全)、
StringBuffer(字符串变量，线程不安全)。

都是final类，不允许被继承。
String是不可变的对象，每次对String类进行改变得时候都会生成一个新得Sting对象，然后将指针指向新得String对象。经常改变字符串长度尽量不要使用String，每次使用都会对系统性能产生影响。放内存中引用得兑现多了以后，JVM就会开始i共做，性能就会降低。

StringBuilder和StringBuffer，每次都会对对象本身进行操作，而不是生成新的得对象并改变对象引用。StringBuffer虽然是线程安全的，但是使用意义不大，并且会拖慢程序速度。其线程安全仅仅是保证jvm不抛出异常知识顺利的往下执行而已，不保证逻辑正确和调用顺序正确。大多时候，我们需要的不仅是线程安全，而是锁。在jdk1.5时，提供了非线程安全的StringBuffer实现，并命名为StringBuilder。从jdk1.5开始，javac把所有用加号连接的String运算都隐式的改写成StringBuilder，循环、递归外的字符串拼接几乎都没有性能损耗。

#### 8.String str="i"与 String str=new String("i")一样吗？

- String str="i"：现在常量池(方法区/永久代)里找有没有“i”这个字符串，如果有就返回这个字符串。没有的话就去创建这个常量，并返回这个引用。
- String str=new String("i")是在java堆上创建一个value为“i”的String对象，jdk1.6及以前的版本会将创建的对象复制到方法区中然后返回方法区中的这个对象的引用，jdk1.7及以后的版本开始“去永久代化”，不会复制到方法区，直接返回java堆中的这个对象的引用。

两者内容一样，==比较为false，equals比较为true，hashcode一样。存储位置不同，前者存储于永久代，后者存储于java堆(jdk1.7及以后)。  

#### 9.如何将字符串反转？
考察StringBuilder的方法使用,自行查阅API并熟练。
```java
String string="123";
String reverse = new StringBuffer(string).reverse().toString();
System.out.println("字符串反转前:"+string);// 字符串反转前:123
System.out.println("字符串反转后:"+reverse);// 字符串反转后:321
```
常用方法
append(T t):添加参数的内容进来
charAt(int index)：返回所在位置的字符
capacity():返回容量
delete(int start, int end) ：删除start到end(前闭后开)的子字符串。
substring(int start, int end) :返回start到end(前闭后开)的子字符串。
toString():……

#### 10.String 类的常用方法都有那些？
考察常规使用，自行查阅API。
charAt(int index) 返回指定索引处的 char 值。
compareTo(String anotherString) 按字典顺序比较两个字符串。
compareToIgnoreCase(String str) 按字典顺序比较两个字符串，不考虑大小写。
contains(CharSequence s) 当且仅当此字符串包含指定的 char 值序列时，返回 true。
endsWith(String suffix) 测试此字符串是否以指定的后缀结束。
equals(Object anObject) 将此字符串与指定的对象比较。
hashCode()  返回此字符串的哈希码。 
int indexOf(int ch) 返回指定字符在此字符串中第一次出现处的索引。 
length() 返回此字符串的长度。
replace(char oldChar, char newChar) 返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
split(String regex) 根据给定正则表达式的匹配拆分此字符串。
……

#### 11.抽象类必须要有抽象方法吗？
*变体：请说抽象类与接口的异同点。*
抽象类可以没有抽象方法。

#### 12.普通类和抽象类有哪些区别？
#### 13.抽象类能使用 final 修饰吗？
#### 14.接口和抽象类有什么区别？
#### 15.java 中 IO 流分为几种？
#### 16.BIO、NIO、AIO 有什么区别？
#### 17.Files的常用方法都有哪些？