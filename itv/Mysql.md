# mysql笔记


#### mysql实现mvcc的原理


#### 慢查询检查
slow_query_log
默认情况下slow_query_log的值为OFF，表示慢查询日志是禁用的，可以通过设置slow_query_log的值来开启，如下所示：
mysql> show variables  like '%slow_query_log%';