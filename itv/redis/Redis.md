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





#### Redis

[原文](https://juejin.cn/post/6844904122622148622)

















