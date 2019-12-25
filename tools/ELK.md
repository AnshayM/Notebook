# 全文搜索引擎 Elasticsearch 入门教程
 
全文搜索属于最常见的需求，开源的 Elasticsearch （以下简称 Elastic）是目前全文搜索引擎的首选。

![elastic](http://www.ruanyifeng.com/blogimg/asset/2017/bg2017081701.jpg)
Elastic 的底层是开源库 Lucene。但是，你没法直接用 Lucene，必须自己写代码去调用它的接口。Elastic 是 Lucene 的封装，提供了 REST API 的操作接口，开箱即用。


# 一、安装
Elastic 需要 Java 8 环境。
- （补充）在这里贴安装java8和ES的教程（wiki上的教程需要同步修改一下，最好都换成单独的。
因为涉及到权限问题，最好放在一个公共的界面。）

[部署过程集合](https://hxgit.hxgis.com/monitor/cdMonitor/wikis/%E9%83%A8%E7%BD%B2%E6%89%8B%E5%86%8C#%E8%BD%AF%E4%BB%B6%E5%87%86%E5%A4%87)

- （补充）相关启动指令也在上诉教程中

# 二、基本概念
## Node 与 Cluster
Elastic 本质上是一个分布式数据库，允许多台服务器协同工作，每台服务器可以运行多个 Elastic 实例。

单个 Elastic 实例称为一个节点（node）。一组节点构成一个集群（cluster）。

## Index
Elastic 会索引所有字段，经过处理后写入一个反向索引（Inverted Index）。查找数据的时候，直接查找该索引。

所以，Elastic 数据管理的顶层单位就叫做 Index（索引）。它是单个数据库的同义词。每个 Index （即数据库）的名字**必须是小写**。

下面的命令可以查看当前节点的所有 Index。  
```$ curl -X GET 'http://localhost:9200/_cat/indices?v'```


## Document
Index 里面单条的记录称为 Document（文档）。许多条 Document 构成了一个 Index。

Document 使用 JSON 格式表示，下面是一个例子。
```$xslt
{
  "user": "张三",
  "title": "工程师",
  "desc": "数据库管理"
}
```
同一个 Index 里面的 Document，不要求有相同的结构（scheme），但是最好保持相同，这样有利于提高搜索效率。

## Type
index下设立type，在早期的设计理念中，等同于数据库下的table，每个index下可以有多个type。
但是在比较新的版本中，每个index下只允许有一个type。

Document 可以分组，比如weather这个 Index 里面，可以按城市分组（北京和上海），也可以按气候分组（晴天和雨天）。这种分组就叫做 Type，它是虚拟的逻辑分组，用来过滤 Document。

不同的 Type 应该有相似的结构（schema），举例来说，id字段不能在这个组是字符串，在另一个组是数值。这是与关系型数据库的表的一个区别。性质完全不同的数据（比如products和logs）应该存成两个 Index，而不是一个 Index 里面的两个 Type（虽然可以做到）。

下面的命令可以列出每个 Index 所包含的 Type。
```
$ curl 'localhost:9200/_mapping?pretty=true'
```

根据规划，Elastic 6.x 版只允许每个 Index 包含一个 Type，7.x 版将会彻底移除 Type。

# 三、新建和删除Index
新建 Index，可以直接向 Elastic 服务器发出 PUT 请求。下面的例子是新建一个名叫weather的 Index。
```
$ curl -X PUT 'localhost:9200/weather'
```
服务器返回一个 JSON 对象，里面的acknowledged字段表示操作成功。
```$xslt
{
  "acknowledged":true,
  "shards_acknowledged":true
}
```

然后，我们发出 DELETE 请求，删除这个 Index。
```$xslt
{
  "acknowledged":true,
  "shards_acknowledged":true
}
```

# java内调用
- 贴代码，演示



# 其他使用——Kibana和logstash
（补充）想想看这个是放在哪个顺序，放在调用前面，作为简单铺垫








[参考网址:阮一峰关于ES的介绍](http://www.ruanyifeng.com/blog/2017/08/elasticsearch.html)
