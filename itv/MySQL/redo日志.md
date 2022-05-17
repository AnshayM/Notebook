## redo log

重做日志，主要是为了应对buffer pool缓冲区刷新到磁盘带来问题。buffer pool也是为了解决读写磁盘的效率问题而在内存中划分的一块区域。同理，写入redo日志时，也不能直接写入到磁盘，而是申请一块redo log buffer的连续内存空间，启动时指定`innodb_log_buffer_size`明确大小，默认为10MB。

redo log是固定大小的，比如配置一组4个文件，每个文件1GB，那么就可以记录最多4GB的redo log，这些文件按照从0开始为下标形成一个环

+ write pos记录当前记录的位置，每次写入时write pos就会向后移动，写到3号文件末尾后会回到0号文件的开头
+ checkpoint记录当前要擦除的位置，可以理解为刷盘的当前位置
+ write pos到checkpoint中间位置为可记录新操作的部分
+ write pos追上checkpoint说明没有位置可以记录新操作，此时需要执行刷盘让checkpoint前进

#### 问题

+ 修改少量数据刷新一个完整页太浪费
+ 单事务包含多个语句，不能确保页相邻，随机IO更加耗时

所以就引进了重做日志来确保每次操作数据页的内容可以被记录下来，可以实现在服务端崩溃后可以通过该重做日志重新更新数据页

#### 优点

+ 占用空间小
+ 顺序IO速度快

#### 格式

<img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/3/4/1694892fdec61898~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp" alt="image_1d36k7d3412oo1c0qcuuben12l79.png-31.3kB" style="zoom:67%;" />

+ type：日志类型。5.7多达53种
+ space ID：表空间ID
+ page number：页号
+ data：记录的内容

#### 组

由于某些操作可能会引起页分裂，所以会产生多条redo日志，这些redo日志被打包成组不可分割。一般是在组的最后一条redo日志的type处进行标识为31的`MLOG_MULTI_REC_END`，崩溃恢复时只有解析到该redo日志时才认为是一组完整的日志，才会进行恢复

#### 刷盘时机

+ redo log buffer有大小限制，一旦达到一半就会刷盘
+ 事务提交时，虽然不会把buffer pool刷新到磁盘，但是redo log是必须刷新到磁盘的，毕竟它是顺序IO会很快
+ 某个脏页刷新到磁盘前，要确保该脏页对应的redo log刷到磁盘
+ 后台也会有个线程，定时每秒刷盘
+ 正常关闭服务器，会立马刷盘