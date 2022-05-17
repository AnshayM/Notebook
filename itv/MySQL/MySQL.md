## 存储引擎

```sql
# 查看所有引擎
show engines;
# 查看当前数据库采取的引擎
show variables like '%storage_engine%';
# 查看表采取的引擎
show table status like 'table_name';
```

+ InnoDB

  MySQL5.5默认存储引擎。支持事务、行锁、崩溃后可恢复、支持外键、支持MVCC

  + redo log保证事务持久性
  + undo log保证事务原子性
  + 锁机制、MVCC保证事务隔离级别
  + 保证了持久性、原子性、隔离性，一致性才能得到保证

+ MyISAM

  MySQL5.5之前默认存储引擎。全文索引、表锁、无事务

  + **无事务支持也不一定比InnoDB快**

## 锁

InnoDB锁种类

+ Table Lock：表锁，锁的是一整张表
+ Record Lock：行锁，锁的是一行记录
+ Gap Lock：间隙锁，锁的是一个范围，不包含记录
+ Next-Key Lock：record+gap临建锁，锁的是一个范围并包含记录本身

MyISAM锁种类

+ Table Lock：MySQL粒度最大的锁，整张表锁住，并发最低，冲突最高，胜在实现简单

## 服务端组成

客户端：连接服务端发送SQL指令

+ 连接器：管理连接，权限校验
+ 查询缓存：命中缓存直接返回结果。要求语句一模一样
+ 分析器：词法分析，语法分析
+ 优化器：执行计划生成，索引选择
+ 执行器：操作存储引擎，返回结果

存储引擎：存储数据，提供读写接口