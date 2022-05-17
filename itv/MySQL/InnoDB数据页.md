## 页

页是InnoDB管理存储空间的基本单位，一个页大小默认为16KB。InnoDB为了不同目的设计了不同类型的页，有存放表空间头部信息的页、存放Insert Buffer信息的页、有存放INODE信息的页、有存放undo log信息的页等等。而用于存放表的行记录就是数据页

<img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/12/17/16f13ee1e2dfac7c~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp" alt="img" style="zoom:25%;" />

#### File Header

文件头部，页的一些通用信息

+ FIL_PAGE_SPACE_OR_CHKSUM页面的校验和
+ FIL_PAGE_OFFSET页号
+ FIL_PAGE_PREV上一个页页号
+ FIL_PAGE_NEXT下一个页页号
+ FIL_PAGE_LSN页面被最后修改对应的日志序列位置
+ FIL_PAGE_TYPE页的类型
+ FIL_PAGE_FILE_FLUSH_LSN仅在系统表空间的一个页中定义，代表文件至少被刷新到了对应的LSN值
+ FIL_PAGE_ARCH_LOG_NO_OR_SPACE_ID页属于哪个表空间

<img src="https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2019/5/8/16a95c10eb9d61ce~tplv-t2oaga2asx-zoom-in-crop-mark:1304:0:0:0.awebp" alt="image_1ca00fhg418pl1f1a1iav1uo3aou9.png-90.9kB" style="zoom:50%;" />

#### Page Header

页面头部，数据页专有的一些信息

+ PAGE_LEVEL表示当前页在B+Tree的层级

+ PAGE_LAST_INSERT表示最后插入记录的位置
+ 。。。

#### Infinum + supernum

两个虚拟行记录，表示最小记录和最大记录

+ 记录头信息5字节。heap_no为0和1，record_type为2和3，infinum的next_record指向插入的最小索引值记录，记录的真实数据到下一条记录的真实数据地址偏移量，存储值为32，代表32字节后的地址就是下一条记录真实数据，其实就是个链表
+ 固定信息8字节，表示infinum或supernum单词

#### User Records

用户记录，存放实际存储的行记录内容

##### Compact行格式

+ 预留位2bit
+ delete_mask1bit，删除标记。被删除的行记录会组成一个垃圾链表，这些空间可以被重用
+ min_rec_mask1bit，b+tree每层非叶子节点中的最小记录都会添加该标记
+ n_owned4bit，表示当前记录拥有的记录数
+ heap_no13bit，表示当前记录在堆的位置信息
+ record_type3bit，表示当前记录类型，0普通记录，1b+tree非叶子节点记录，2最小记录，3最大记录
+ next_record16bit，表示下一条记录的相对位置
+ 行记录列的值
+ 其他信息

#### Free Space

空闲空间，整个页中未被使用的部分，插入一条记录就分配一条记录的内存空间给User Records用

#### Page Directory

页面目录，页中的某些记录相对位置

+ 将User Records中记录按组进行划分（包括infinum和supernum，不包括delete_mask为1的）
+ 每个组最后一条记录（主键值最大的记录）的头信息中的n_owned属性表示该组内有几条记录
+ 将每个组的最后一条记录偏移量提取出来放到Page Directory页目录中，也被称为slot槽

注意⚠️

+ 初始情况下，infinum和supernum各自组成一组
+ 插入数据后，会在页目录找到主键值比本记录的主键值大并且差值最小的槽，然后把slot对应的n_owned加1
+ supernum所在分组记录数可以在1-8条，剩下分组记录数可以在4-8条

数据页查找过程

+ 通过二分法确定记录所在的槽，并找到槽所在分组的主键值最小的那条记录
+ 沿着最小的那条记录的next_record组成的链表遍历所在组的各个记录

<img src="http://120.24.5.130:9000/typora/bf687b10-35c2-43c3-8e36-857eb8a94824.awebp" alt="image_1d6g64af2sgj1816ktl1q22dehp.png-189.1kB" style="zoom: 50%;" />

#### File Trailer

文件尾部，校验页是否完整

InnoDB以页为单位将数据加载到内存中处理，如果该页在内存中被修改后，由于断电等意外操作就需要校验一个页是否完整，所以每个页尾部就有一个File Trailer的尾部

+ 前4字节代表页的校验和
+ 后4字节代表页面最后被修改时对应的日志序列位置LSN

