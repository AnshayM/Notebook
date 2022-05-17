## undo log

回滚日志，类似于下棋时的悔棋操作。确保了事务的原子性，要么全部完成，要么全部不完成。但回滚肯定是回滚那些对数据库有影响的记录，所以就需要记录事务执行过程中的增、删、改操作，像查就不需要记录。

+ 事务执行过程中出现了错误，断电，宕机等等
+ 事务执行过程中，人为手动rollback触发

#### 事务id

##### 分配时机

+ 只读事务是不能对表进行增删改，但临时表可以操作，所以只要不对临时表操作就不会分配事务id，一旦操作立马分配事务id
+ 读写事务开启后也不是立马分配，是在第一次对表做增删改操作才会分配事务id

##### 生成策略

+ 全局变量，每次自增1分配给事务
+ 变量值为256倍数时，会把该值刷到系统表空间页号5的页面的`Max Trx ID`处，占8字节
+ 下一次启动时，会把`Max Trx ID`加载进内存，并将该值加256赋值给全局变量

##### 存放位置

行记录除开记录的额外信息、row_id(无唯一索引时分配)、trx_id、rollpointer、用户列信息

#### 日志格式

增删改操作，每一条记录的改动对应一条undo日志，一个事务的多条行记录变更过程中，undo日志从0开始编号，这个编号为undo no

##### 插入操作

undo日志存放到类型为`FILE_PAGE_UNDO_LOG`的页中，事务操作的每张表在`information_schema`库的`innodb_sys_tables`表其实是有一个唯一的`table id`。如下图所示

<img src="http://120.24.5.130:9000/typora/c9cd90c4-79b6-49e2-a7cb-3d7b91607fb4.awebp" alt="image_1d65eln739ukbei9pgid81pr57o.png-112.4kB" style="zoom:67%;" />

那么行记录的roll_pointer指针其实就是一个指向undo_log的指针，只要一条记录被插入后，生成的undo log存放在`FILE_PAGE_UNDO_LOG`页，插入的记录存放在`FILE_PAGE_INDEX`页

##### 删除操作

我们知道插入行记录时会根据记录头的`next_record`属性组成一个单向链表，这个会串成一个`正常记录的链表`；而删除的行记录，也会用`next_record`属性串成一个`垃圾链表`，主要是为了后续插入时空间的再利用，不用再分配了。页的`page_header`部分有一个`PAGE_FREE`属性指向了这个垃圾链表头节点

<img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/6/24/16b875af99f1eb4e~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp" alt="image_1d6abjg9n1kocq5d10j6250164v9.png-62.8kB" style="zoom:67%;" />

但其实删除行记录时并不是直接把`正常记录链表`节点移动到`垃圾链表`上，删除过程有两个阶段

+ 将行记录的`delete_mask`标记为1，其他不做修改(但其实会修改记录的trx_id、roll_pointer隐藏列的值)。这个阶段为`delete_mask`阶段，属于删除的中间状态
+ 当删除语句所在事务提交后，才会有专门线程将这些`delete_mask`标记为1的节点从正常链表移除移动到垃圾链表上，这步是头插法。除此之外，还会调整，页的一些信息，比如页面用户记录数、上次插入的位置、垃圾链表头节点指针`PAGE_FREE`还有些页目录信息等等。这个阶段为`purge`阶段