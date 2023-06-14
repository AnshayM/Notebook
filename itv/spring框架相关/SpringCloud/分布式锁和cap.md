## CAP无法同时满足

**一致性**Consistency：分布式环境下多个节点的数据是否强一致。
**可用性**Avaliability：分布式服务能一直保证可用状态。当用户发出一个请求后，服务能在有限的时间内返回结果。
**分区容忍性**Patition Tolerance：对网络分区的容忍性。

**BASE理论**

**基本可用**(Basically Available)：指分布式系统在出现故障时，允许损失部分的可用性来保证核心可用
**软状态**(Soft State)：指分布式系统存在中间状态，该中间状态不会影响到系统的整体可用性
**最终一致性**(Eventual Consistency)：指分布式系统中的所有副本数据经过一定时间后，最终能够达到一致的状态

## 分布式事务、分布式锁

### 分布式事务解决方案

工程领域讨论的是强一致性和最终一致性的解决方案：

1. **两阶段提交**(2PC,Two-phase Commit)方案-强一致性

    1.1 **准备阶段**-事务协调器给每个参与者发送prepare消息，每个参与者要么直接返回失败(ex：权限校验是阿比)要么本地执行事务，写本地redo和undo日志，但不提交。
    1.2 **提交阶段**-如果收到了失败信息/超时，给每个参与者发送回滚消息；否则发送提交消息。参与者根据指令执行提交/回滚操作。

    

    通过提交分阶段和记日志的方式，记录下事务提交所处的状态。组件宕机/重启后，可以通过日志恢复事务提交的阶段状态，并在这个状态重试。

    Coordinator(协调器)重启后，通过日志判断当前所处状态。
    **prepare**--有组件prepare成功，或所有节点prepare成功但是没有下发commit，状态恢复后下发rollback；
    **prepareAll**--给所有节点下发commit。数据库节点需要保证commit**幂等**。

    问题：同步阻塞、数据不一致、单点问题
    升级为3PC后，增加超时机制、两阶段之间插入准备阶段。

2. **ebay事件队列方案**

    将需要分布式处理的任务通过消息/日志的方式异步执行，消息/日志可以存到本地文件、数据库、消息队列，再通过业务规则进行失败重试。它要求服务的接口是**幂等**的。

    可能需要一个操作记录表来判定某些操作是否已经完成，以此来实现业务的幂等性。

3. **TCC**（Try-Confirm-Cancel）补偿模式

    服务A-B-C的调用链，在后段失败是，需要对前面的操作进行回滚，保证整个微服务系统的数据的一致性。

    关键要素：

    1. 服务调用链必须被记录下来
    2. 每个服务提供着都需要提供一组业务逻辑相反的操作，互为补偿，同时回滚操作要保证幂等。
    3. 必须按失败原因执行不同的回滚策略。

4. **缓存数据最终一致性**

    当修改了落库数据后，但是从缓存拿到的还是过时数据，造成了数据不一致的问题。

    1. 缓存设置过期时间。过期后重新获取

    2. 更新数据库后同步清除缓存数据。让下次请求从数据库获取，并同步到缓存。

这几种方式，根据具体的场景来选择。



### 分布式锁的几种实现方式

大概就是三种

基于缓存：redis，memcached
基于数据库：
基于zookeeper



#### 1.使用redis实现分布式锁

1.1 WATCH，MULTI, EXEC, DISCARD事务机制实现分布式锁

``` c
MULTI
some redis command
EXEC
```

在multi和exec包裹的redis命令，保证所有事务内的命令会串行执行，不会在执行过程中被其他客户端打断。
watch命令能监视某个键，当事务执行时，被监视的键被其他客户端修改了值，事务运行失败，返回响应错误。
discard用于取消事务，放弃执行事务块内所有命令

```python
# -*- coding: utf-8 -*-
def acqure_lock_with_watch(conn, lockname, acquire_timeout=10):
   pipe = conn.pipeline()
   end = time.time() + acquire_timeout
   lockname = 'lock:' + lockname
   while time.time() < end:
       try:
           pipe.watch(lockname)
           pipe.multi()  # 开启事务
           # 事务具体内容，对lockname的值进行更新
           pipe.execute()
           return True
       except redis.exceptions.WatchError:
           # 事务运行期间，有其他客户端改变了lockname的值，抛出异常，进行重试操作
           pass
   return False
```



1.2 sentnx实现分布式锁

sentnx：(SET if Not exists)仅在指定键不存在时，向redis添加一个键值对，返回1。若已存在，则不做处理，返回0。
redis客户端保证对统一键名称，多个客户端同时设置其值时只有一个客户端能够设置成功的原子性。

```python
# -*- coding: utf-8 -*-
def acquire_lock_with_timeout(
       conn, lockname, acquire_timeout=10, lock_timeout=10): 
   identifire = str(uuid.uuid4())
   lockname = 'lock:' + lockname
   lock_timeout = int(math.ceil(lock_timeout)) 
   end = time.time() + acquire_timeout
   while time.time() < end:
       if conn.setnx(lockname, identifire):  # 以锁名称为键，uuid的值为值，redis服务 
器setnx保证了只能有一个客户端成功设置键的原子性
           conn.expire(lockname, lock_timeout)  # 设置键的过期时间，过期自动剔除，释放 
锁
           return identifire
       elif not conn.ttl(lockname):  # 当锁未被设置过期时间时，重新设置其过期时间 
           conn.expire(lockname, lock_timeout)
       time.sleep(0.001) 
   return False
```

使用setnx的原子特性和redis键的过期特性，实现了自动过期释放的分布式锁。
注：如果不设置过期策略，当获取锁的客户端宕机，其他尝试获取锁的线程会一直等待，造成死锁。

1.3 锁的释放

释放锁的过程，首先检查客户端是否仍然持有该锁，如果有，则在事务中删除键值对，释放锁的所有权。

```python
# -*- coding: utf-8 -*-
def release_lock(conn, lockname, identifire):
   pipe = conn.pipeline(True)
   lockname = 'lock:' + lockname
   while True:
       try:
           pipe.watch(lockname)  # 监视锁的键，在锁释放过程中改变了键的值时得到相应通知
           if pipe.get(lockname) == identifire:  # 检查客户端是否仍然持有该锁
               pipe.multi()
               pipe.delete(lockname)  # 删除键，释放锁
               pipe.execute()
               return True
           pipe.unwatch()
           break
       except redis.exceptions.WatchError:
           pass  # 释放锁期间，有其他客户端改变了键值对，锁释放失败，进行循环
   return False
```





#### 2. 使用memcached实现分布式锁

memcached的add命令，当指定的key不存在时，进行添加，且保证了执行的原子性。利用这个特性，可以实现一个分布式锁的实现。

```python
def acquire_lock_with__memcached_timeout(
       conn, lockname, acquire_timeout=10, lock_timeout=10):
   identifire = str(uuid.uuid4())
   lockname = 'lock:' + lockname
   lock_timeout = int(math.ceil(lock_timeout))
   end = time.time() + acquire_timeout
   while time.time() < end:
       # 过期时间保证了客户端崩溃时仍能在超过过期时间后正常释放锁
       # 以锁名称为键，uuid的值为值，memcached服务器add保证了只能有一个客户端成功设置键的原 
子性
       if conn.add(lockname, identifire, lock_timeout): 
           return identifire
       time.sleep(0.001) 
   return False
```



#### 3. 使用zookeeper实现分布式锁

zk可以利用其高级特性，能够实现更复杂的锁特性。

**排他锁**

获取锁：zookeeper实现分布式锁利用了其临时子节点的如下特性：
在/exclusive_lock节点下创建临时节点/exclusive_lock/lock，zk会保证所有的客户端中，最终只有一个客户端能够创建成功，即可以认为该客户获取了锁。同时，其他客户端需要到/exclusive_lock节点上注册一个子节点变更的wacher监听，以便实时监听到lock节点的变更情况。

释放锁：
因为获取锁时，创建的是一个临时节点/exclusive_lock/lock，因此在如下情况下，都有可能释放锁：

- 获取锁的机器发生宕机，临时节点被zk移除
- 正常执行业务逻辑后，客户端主动删除临时节点

任何情况下，lock节点被移除，zk都会通知所有注册对应监听的客户端，这些客户端之后会重新发起分布式锁的获取流程。

**共享锁**

获取锁：所有客户端到/shared_lock节点下创建一个临时顺序节点，/shared_lock/[hostname]-请求类型(W/R)-序号，该节点代表了一个共享锁。
读请求：则创建/shared_lock/192.168.0.1-R-000000000001；
写请求：则创建/shared_lock/192.168.0.1-W-000000000001；

判断读写顺序

- 1创建完监听后，获取/shared_lock节点下的所有子节点，对该节点注册子节点变更的watcher监听

- 2确定自己的节点序号在所有节点的顺序

- 3对于读请求

    如果没有比自己序号小的子节点，或者比自己小的都是读请求，表明成功获取共享锁，执行读请求
    如果有比自己序号小的子节点中有写请求，进入等待。

    对于写请求
    如果自己不是序号最小的子节点，进入等待。

    收到watcher通知后，重复步骤1-4

释放锁：删除对应节点的数据节点即可



#### 4.数据库落库

创建一张表记录加锁的资源，通过唯一索引来防重复

```sql
CREATE TABLE `database_lock` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `resource` int NOT NULL COMMENT '锁定的资源',
    `description` varchar(1024) NOT NULL DEFAULT "" COMMENT '描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uiq_idx_resource` (`resource`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据库分布式锁表';

-- 获取锁时插入数据
INSERT INTO database_lock(resource, description) VALUES (1, 'lock');
```

- 需要手动维护失效时间
- 依赖数据库，避免单点需要做备库，提高了业务复杂性
- 非阻塞的，执行失败后需要手动做循环处理。
- 非可重入的

#### 5. 使用三方库 Curator

这方面知识比较少，待补充





