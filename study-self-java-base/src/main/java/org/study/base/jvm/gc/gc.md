### Default garbage collectors
* Java 7 - Parallel GC
* Java 8 - Parallel GC
* Java 9 - G1 GC
* Java 10 - G1 GC

###显示gc日志
* -XX:+PrintGC 输出GC日志
* -XX:+PrintGCDetails 输出GC的详细日志
* -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
* -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
* -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
* -Xloggc:../logs/gc.log 日志文件的输出路径

####这里使用如下的参数来进行日志的打印：
* -XX:+PrintGCDateStamps -XX:+PrintGCDetails -Xloggc:./gclogs

* -XX:+PrintGCDetails -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8 -XX:NewSize=10M -XX:MaxNewSize=10M

######参数解释：
* -XX:+PrintGCDetails 启用日志
* -XX:-UseAdaptiveSizePolicy 禁用动态调整，使SurvivorRatio可以起作用
* -XX:SurvivorRatio=8 设置Eden:Survivior=8
* -XX:NewSize=10M -XX:MaxNewSize=10M 设置整个新生代的大小为10M
* 默认垃圾收集器为：Parallel Scavenge

#### gc回收算法
* “标记-清除”（Mark-Sweep）
  * 算法分为“标记”和“清除”两个阶段：首先标记出所有需要回收的对象，在标记完成后统一回收掉所有被标记的对象

* “复制”（Copying）
  * 它将可用内存按容量划分为大小相等的两块，每次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后再把已使用过的内存空间一次清理掉。这样使得每次都是对其中的一块进行内存回收，内存分配时也就不用考虑内存碎片等复杂情况，只要移动堆顶指针，按顺序分配内存即可，实现简单，运行高效。
  * 现在的商业虚拟机都采用这种收集算法来回收新生代，IBM的专门研究表明，新生代中的对象98%是朝生夕死的，所以并不需要按照1∶1的比例来划分内存空间，而是将内存分为一块较大的Eden空间和两块较小的Survivor空间，每次使用Eden和其中的一块Survivor。当回收时，将Eden和Survivor中还存活着的对象一次性地拷贝到另外一块Survivor空间上，最后清理掉Eden和刚才用过的Survivor的空间。HotSpot虚拟机默认Eden和Survivor的大小比例是8∶1，也就是每次新生代中可用内存空间为整个新生代容量的90%（80%+10%），只有10%的内存是会被“浪费”的。当然，98%的对象可回收只是一般场景下的数据，我们没有办法保证每次回收都只有不多于10%的对象存活，当Survivor空间不够用时，需要依赖其他内存（这里指老年代）进行分配担保（Handle Promotion）。
  * 在对象存活率较高时就要执行较多的复制操作，效率将会变低。更关键的是，如果不想浪费50%的空间，就需要有额外的空间进行分配担保，以应对被使用的内存中所有对象都100%存活的极端情况，所以在老年代一般不能直接选用这种算法。
  * -XX:SurvivorRatio=4
  * 设置年轻代中Eden区与Survivor区的大小比值。设置为4，则Eden区与两个Survivor区的比值为4:1:1，一个Survivor区占整个年轻代的1/6
  
* “标记-整理”（Mark-Compact）
  * 此算法结合了“标记-清除”和“复制”两个算法的优点。也是分两阶段，
    * 第一阶段从根节点开始标记所有被引用对象，
    * 第二阶段遍历整个堆，把清除未标记对象并且把存活对象“压缩”到堆的其中一块，按顺序排放。此算法避免了“标记-清除”的碎片问题，同时也避免了“复制”算法的空间问题。