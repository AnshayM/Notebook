# Mybatis相关问题

#### mybatis的动态代理怎么实现的

mybatis动态代理要从启动开始说起，启动的时候去加载mybatis的配置文件（原生mybatis），然后会开始解析mybatis的配置文件中的标签，当读到mapper标签的时候就会去解析mapper文件找到对应的接口然后MapperRegistry通过反射生成MapperProxyFactory，MapperProxyFactory里面就包含了mapper的动态代理类MapperProxy，当我们getMapper的时候获取的就是动态代理类，这时候就可以执行代理方法然后执行具体的sql。

1.夹在mybatis的配置文件
2.解析配置文件中的标签，读取到mapper标签时，去解析mapper文件
3.找到对应的借口，通过反射生成MapperProxyFactory，其中包含了mapper的动态代理类MapperProxy

getMapper时获取的就是动态代理类，这时候执行代理方法然后执行具体的sql



#### 一级缓存和二级缓存

##### 原理和实现

MyBatis中一级缓存默认开启，并且无法关闭。
**一级缓存**是指sqlSession级别的缓存，在同一个sqlSession中，进行相同的sql语句查询时，第二次直接从缓存中获取。一级缓存最多可以缓存1024条sql。
二级缓存，指可以跨sqlSession的缓存，是mapper的缓存，对于mapper级别的缓存不同的sqlSession是可以共享的。

缓存的数据结构是map
key：mapperId+offset+limit+sql+所有入参
value：用户信息

在一级缓存中，两次相同的sql中间出现commit操作(修改、添加、删除),本sqlsession中的一级缓存区域全部清空，下次再去缓存中查询不到所以要去数据库查询，然后再写入到缓存中。

在**二级缓存**中，mapper以命名空间为单位创建缓存数据结构，结构是map。mybatis的二级缓存是通过CachingExecutor实现的，CachingExecutor是Executor的代理对象，所有的查询操作，在CachingExecutor中都会优先匹配缓存中是否存在，不存在则调用Executor去查询数据库，之后CachingExecutor会将Executor返回的查询结果放置在缓存中，然后再返回给用户。

开启二级缓存需要的配置
1/MyBatis全局配置中启用二级缓存配置
2/在对应的Mapper.xml中配置cache节点
3/在对应的select查询节点中添加useCache=true

二级缓存可以通过实现*org.apache.ibatis.cache.Cache*接口自定义缓存。也可以使用三方内存缓存库，比如Memcached等。







