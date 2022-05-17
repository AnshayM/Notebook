## MVCC

Multi-Version Concurrency Control多版本并发控制。特指在READ_COMMITTED、REPEATABLE_READ两大事务隔离级别下执行`SELECT`操作时访问记录的版本链的过程。可以使得不同事务的读写、写读操作并发进行，提升系统性能。

## 事务并发问题

##### 脏写

Dirty Write，指一个事务修改了另一个事务没有提交修改过的数据。

##### 脏读

Dirty Read，指一个事务读取了另一个事务没有提交修改过的数据。

##### 不可重复读

Non-Repeatable Read，指一个事务读取了另一个事务提交修改过的数据，并且其他事务修改后，均能读取到最新数据

##### 幻读

Phantom，指一个事务根据条件查询出一些数据，之后其他事务向该条件范围内又插入一些数据，原先事务再次查询也能查询出来

## 事务隔离级别

| 隔离级别                 | 脏写 | 脏读 | 不可重复读 | 幻读 |
| ------------------------ | ---- | ---- | ---------- | ---- |
| 读未提交Read Uncommitted | ✘    | ✔︎    | ✔︎          | ✔︎    |
| 读已提交Read Committed   | ✘    | ✘    | ✔︎          | ✔︎    |
| 可重复读Repeatable Read  | ✘    | ✘    | ✘          | ✔︎    |
| 串行化Serializable       | ✘    | ✘    | ✘          | ✘    |

## 数据库不同

+ Oracle只支持读已提交和串行化
+ MySQL支持读未提交、读已提交、可重复读、串行化。并且默认隔离级别可重复读可以避免幻读

## MVCC原理

InnoDB存储引擎的行记录包含两个隐藏列trx_id、roll_pointer

+ trx_id：每次事务操作对某条聚簇索引进行修改，都会把该事务的事务id写入到trx_id隐藏列
+ roll_pointer：每次对某条聚簇索引进行修改，都会把旧记录值写入到undo log中，roll_pointer指向undo log，并且每次修改后都会将旧的值再次写入到一条undo log，undo log之间通过roll_pointer指针连起来的，也就是undo log之间通过roll_pointer串成了一个版本链，其中链的头节点就是行记录本身是最新的记录。每个版本就有trx_id事务id隐藏列

<img src="http://120.24.5.130:9000/typora/d01b17e0-d824-4bb8-879d-d10140f8f7b4.awebp" alt="image_1d8po6kgkejilj2g4t3t81evm20.png-81.7kB" style="zoom:67%;" />

## ReadView

+ 对于读未提交，由于每次读取都是最新版本值，所以每次读取都是版本链的头节点即行记录本身
+ 对于串行化，由于事务都是串行执行，所以都是通过加锁来访问的
+ 对于读已提交和可重复读，都必须要保证读取到事务提交后的数据结果，也就是说其他事务修改后未提交是不能看到的，所以就需要判断当前版本链中哪个版本对当前事务是可见的，所以引出了ReadView的概念

##### 属性

+ m_ids：表示在生成ReadView时正在活跃的读写事务id集合
+ min_trx_id：表示在生成ReadView时正在活跃的读写事务最小id，也就是m_ids集合最小值
+ max_trx_id：表示生成ReadView时系统应该分配给的下一个事务id。**不是m_ids集合最大值**
+ creator_trx_id：表示生成ReadView的事务的事务id

##### 可见性

+ 如果被访问版本的trx_id与ReadView的creator_trx_id相同时，意味着当前事务在访问它自己修改过的记录，所以该事务对当前版本是可见的

+ 如果被访问版本的trx_id小于ReadView的min_trx_id，意味着生成该版本的事务在当前事务生成ReadView前就已提交，该版本对当前事务是可见的

+ 如果被访问版本的trx_id大于ReadView的max_trx_id，意味着生成该版本的事务在当前事务生成ReadView后才提交，该版本对当前事务是不可见的（说明生成ReadView时还没提交）
+ 如果被访问版本的trx_id介于min_trx_id和max_trx_id之间，需要再判断trx_id是否在m_ids集合中，如果存在说明当前事务生成ReadView时生成该版本的事务是处于活跃状态的，也就是未提交那么该版本对当前事务是不可访问的；如果不存在，说明当前事务生成ReadView时生成该版本的事务是提交的，那么该版本对当前事务是可访问的
+ 如果被访问版本对于当前事务是不可见的，那就会顺着这条MVCC版本链依次向后遍历访问，如果找不到任意一条undo记录就说明该条记录对于当前事务是不可见的

##### 区别

+ Read Committed每次读取数据前生成一个ReadView
  + 正因为每次读取前都生成ReadView，确保了在两次读取之间其他事务的提交可以更新min_trx_id(也就是覆盖了提交后的事务)，这样顺着版本链就能访问到生成视图前最新提交的版本
+ Repeatable Read第一次读取数据前生成一个ReadView
  + 正因为第一次读取数据就生成了ReadView，确保了其他事务不管再怎么修改数据提交，当前事务的ReadView视图的min_trx_id不会改变，每次顺着版本链访问总能访问到生成视图前的最后一次提交的版本