Reids全称Remote Dictionary Server(远程字典服务)

#### 为什么Redis快

1. Redis采用ANSI(一种标准) C语言编写，底层代码执行效率高。使用C语言开发的库，没有太多运行时依赖，兼容性好，稳定性高。
2. redis是基于内存的数据库，避免了磁盘IO
3. 通过key-value的方式存储数据，数据操作复杂度为O(1)
4. 多个socket网络链接服用同一个线程，在同一个线程中处理多个I/O请求，减少 了网络I/O消耗。

#### [数据结构](https://snailclimb.gitee.io/javaguide/#/docs/database/redis/redis-questions-01?id=redis-%e5%b8%b8%e8%a7%81%e6%95%b0%e6%8d%ae%e7%bb%93%e6%9e%84%e4%bb%a5%e5%8f%8a%e4%bd%bf%e7%94%a8%e5%9c%ba%e6%99%af%e5%88%86%e6%9e%90)

**字符串**
常用命令：set, get,strlen,exists,decr,incr,setex
应用场景：应用于需要计数的场景，比如用户访问次数、热点文章的点赞转发数量
(一个字符串最大存储512M的数据量大小)

```c
set key value #设置 key-value 类型的值
get key # 根据 key 获得对应的 value
exists key  # 判断某个 key 是否存在
strlen key # 返回 key 所储存的字符串值的长度。
del key # 删除某个 key 对应的值
expire key  60 # 数据在 60s 后过期
```

**列表**
常用命令：rpush, loop, lpush, lrange, llen
应用场景：发布与订阅、消息队列、慢查询

**hash**
常用命令：hset, hmset, hexists, hget, hgetall,hkeys,hvals
应用场景：系统中对象数据的存储

**集合(set)**(底层是使用hash表构造的)
常用命令：sadd, spop, smembers, sismember,scard,sunion等
应用场景：需要存放的数据不能重复，以及需要获取多个数据源交集和并集等场景

```
sadd mySet value1 value2 # 添加元素进去 --(integer) 2
sadd mySet value1 # 不允许有重复元素 --(integer) 0
smembers mySet # 查看 set 中所有的元素 --
scard mySet # 查看 set 的长度 -- (integer) 2
sismember mySet value1 # 检查某个元素是否存在set 中，只能接收单个元素
sinterstore mySet3 mySet mySet2 # 获取 mySet 和 mySet2 的交集并存放在 mySet3 中
```



**有序集合sorted Set**
常用命令：zadd,zcard,zscore,zrange,zrevrange,zrem等
应用场景：需要对数据根据某个权重进行排序的场景，比如在直播系统中，实时排行信息，包括在线用户列表、各种礼物排行榜，弹幕消息(根据时间等排序)等。



#### 持久化

Redis不支持事务的回滚机制，当事务发生错误时，会继续执行下去，知道事务队列中所有命令都执行完成，

**RDB（Redis DataBase）** ：
将当前进程的数据生成快照保存在磁盘上。触发方式分手动和自动。因为持久化操作与命令是不同步的，所以无法保证事务的持久性。

**AOF（Append only File）** ：
通过日志的方式记录每个写操作，弥补了RDB在数据一致性上的缺陷
todo 补全





#### Redis过期键的删除策略

- **定时删除**：设置键的同时，配置一个定时器timer，让定时器在过期时间来临时立即执行删除操作。

    优点：对内存友好
    缺点：对CPU时间非常不友好
    当服务器创建大量的定时器，服务器处理命令请求的性能会降低。所以Redis目前没有采用。

- **惰性删除**：放任过期键不管，每次从键空间获取键时，检查获得的键是否过期，过期就删除，否则返回。

    优点：对CPU时间非常友好
    确定：对内存非常不友好
    过多的过期键保留会占用内存资源。

- **定期删除**：每隔一段时间就对数据库进行一次检查，删除里面的过期键。
    每隔一段时间执行一次删除过期键操作，并通过限制删除操作执行的时常和频率来减少删除操作对CPU时间的影响。通过定期删除过期键，也有效减少了过期键带来的内存浪费。

Redis服务器使用的是惰性删除+定期删除策略

###### **惰性删除策略**

由expireIfNeeded函数实现.所有读写数据库的Redis操作在执行之前都会调用expireIfNeeded函数对键进行检查
已过期：删除
未过期：不做处理

###### **定期删除策略**

由activeExpireCycle函数实现.每当Redis服务器周期性操作serverCron函数时，acticeExpireCycle函数就会被调用，会在规定的时间内，分多次遍历服务器中的各个数据库，从数据库中的expires字典汇总随机检查一部分键的过期时间，并删除其中的过期键。

###### **Redis的回收策略（淘汰策略-简单了解）**

关联问题，mysql里有200w数据，redis只保存20w数据，如何保证redis中数据都是热点数据

- Volatile-lru(Least recently used): 从配置了过期时间的数据中，选择最近最少使用的
- volatile-ttl: 从配置了过期时间的数据中，将要过期的
- Volatile-random: 从配置了过期时间的数据中，随机
- Alleys-lru: 内存不足容纳写入新数据时，从所有数据中，挑选最少使用的(最常用的)
- Allkeys-random：所有数据中，随机淘汰
- No-enviction: 禁止驱逐数据，内存不足时写入操作报错。

4.0版本后新增

- Volatile-lfu(least frequently used): 从已设置过期时间的数据集中，最不经常使用的
- Alleys-lfu(least frequently used): 从所有数据中，移除最不经常使用的

###### RDB对过期键的处理

**生成RDB文件**
生成RDB文件，执行svae或者bsave命令创建一个新的rdb文件时，程序会对数据库中的键做检查，已过期的不会创建到新的rdb文件中。

**载入RDB文件**
启动Redis服务器时，如果只开启了rdb持久化，服务器会载入rdb文件

- 如果服务器以主模式运行，载入rdb文件时，程序会对稳重保存的键进行检查，未过期的键会保存到数据库中，过期会忽略
- 如果服务器以从服务器，在载入edb文件时，文件中所有键，不论是否过期都会被载入到数据库中。

因为主从服务器在进行数据同步(完成重同步)时，从服务器的数据库会被清空

###### AOF对过期键的处理

**aof文件写入**
服务器开启了AOF，过期键被惰性删除或者定期删除后，程序会想aof文件中追加一条del命令，显式记录该键已被删除

- 从数据库中删除message键
- 追加一条Del message命令到AOF文件，向执行GET message的客户端返回空回复。

**aof文件重写**
执行aof文件重写时，会对数据库的键进行检查，已过期的不会被保存到重写后的aof文件中。

###### **主从服务对过期键的处理**

- 主服务器在删除一个过期键后，会显式地向所有从服务器发送一个DEL命令，告知从服务器删除这个过期键
- 从服务器在执行客户端发送的读命令时，即使发现该键已过期也不会删除该键，照常返回该键的值
- 从服务器只有在接收到主服务器发送的DEL命令后，才会删除过期键

[原文](https://juejin.cn/post/6844904122622148622)







#### Redis部署模式



Redis支持多种部署模式，单机、主从、哨兵、集群

**单机**：
启动编译即可
配置redis.conf文件``` demonized yes```

**主从模式**：
设置一台主库进行读写，多台从库进行备份操作。主库的写操作会被分到从库。主库写，从库读，读写分离。
如果主库宕机，集群不能执行写操作，不影响从库读取；从库宕机，不影响主库其他的从库和主库。

```java

```



**哨兵模式：**
比主从模式，解决主库宕机后无法写数据的问题。

**集群模式**
内存有限下，一台服务器无法存储所有数据，采用集群模式，水瓶扩展，将数据分片分散到多台机器上









