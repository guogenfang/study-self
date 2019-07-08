package org.study.base.jvm.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * [简要描述]: 测试对象引用（强引用，软引用，弱引用，虚引用）、垃圾回收、heap space outOfMemory
 * JVM的垃圾回收机制，在内存充足的情况下，除非你显式调用System.gc()，否则它不会进行垃圾回收；在内存不足的情况下，垃圾回收将自动运行
 * JVM参数 -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:-UseAdaptiveSizePolicy -Xloggc:./study-self-java-base/src/main/java/org/study/base/jvm/gc/gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./study-self-java-base/src/main/java/org/study/base/jvm/gc/dump.bin -XX:SurvivorRatio=8 -XX:NewSize=10M -XX:MaxNewSize=10M -Xmx100M
 * <p>
 * 级别                什么时候被垃圾回收          用途              生存时间
 * 强引用                  从来不会            对象的一般状态      JVM停止运行时终止
 * 软引用                 在内存不足时         对象简单？缓存         内存不足时终止
 * 弱引用                 在垃圾回收时         对象缓存               gc运行后终止
 * 虚引用                 在实例化后           sun.misc.Cleaner
 *
 * @Author ggf
 * @Date 2019/6/27 10:38
 **/
public class ReferenceTest {
    private static List<Object> map = new ArrayList<>();

    // 消耗大量内存
    public static void drainMemory() {
        String[] array = new String[1000 * 500];
        for (int i = 0; i < 500 * 1000; i++) {
            array[i] = Math.random() + "ffffffffadf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffssssssssssssssssssss";
            try {
                Thread.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(array.length);
    }

    /**
     * [简要描述]：
     * date虽然设为null，但由于JVM没有执行垃圾回收操作，MyDate的finalize()方法没有被运行
     *
     * @return void
     * @Author ggf
     * @Date 13:41 2019/6/27
     * @Param []
     **/
    public static void g1() {
        MyDate date = new MyDate();
        date = null;
    }

    /**
     * [简要描述]：显式调用垃圾回收
     *
     * @return void
     * @Author ggf
     * @Date 13:41 2019/6/27
     * @Param []
     **/
    public static void g2() {
        MyDate date = new MyDate();
        date = null;
        System.gc();
    }

    /**
     * [简要描述]：测试内存溢出
     *
     * @return void
     * @Author ggf
     * @Date 13:43 2019/6/27
     * @Param []
     **/
    public static void g3() {
        while (true) {
            MyDate date = new MyDate(Math.random() + "ffffffffadf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫adf阿道夫ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffssssssssssssssssssss");
            map.add(date);
        }
    }

    /**
     * [简要描述]：对象强引用,不回收
     *
     * @return void
     * @Author ggf
     * @Date 13:49 2019/6/27
     * @Param []
     **/
    public static void strongReference() {
        MyDate date = new MyDate();
        System.gc();
    }

    /**
     * [简要描述]：隐式调用垃圾回收
     * 虽然没有显式调用垃圾回收方法System.gc()，但是由于运行了耗费大量内存的方法，触发JVM进行垃圾回收。
     *
     * @return void
     * @Author ggf
     * @Date 13:41 2019/6/27
     * @Param []
     **/
    public static void g5() {
        MyDate date = new MyDate();
        date = null;
        drainMemory();
    }

    /**
     * [简要描述]：软引用 在内存不足时，软引用被终止。由JVM决定运行 If(JVM.内存不足()) date = null; System.gc();
     *
     * @return void
     * @Author ggf
     * @Date 13:40 2019/6/27
     * @Param []
     **/
    public static void softReference() {
//        MyDate date = new MyDate();
        SoftReference ref = new SoftReference(new MyDate());
        drainMemory();
    }

    /**
     * [简要描述]：弱引用 在JVM垃圾回收运行时，弱引用被终止.
     *
     * @return void
     * @Author ggf
     * @Date 14:19 2019/6/27
     * @Param []
     **/
    public static void weakReference() {
        WeakReference ref = new WeakReference(new MyDate());
        System.out.println("before gc:" + ref.get());
        System.gc();
        System.out.println("after gc:" + ref.get());
    }

    /**
     * [简要描述]：虚引用 在实例化后，就被终止了。
     * 等同于：
     * MyDate date = new MyDate();
     * date = null;
     * 永远get null，因为 无法通过虚引用获取与之关联的对象实例
     * 其意义在于说明一个对象已经进入finalization阶段，可以被gc回收，用来实现比finalization机制更灵活的回收操作。
     * @return void
     * @Author ggf
     * @Date 14:19 2019/6/27
     * @Param []
     **/
    public static void phantomReference() {
        System.out.println("----------phantom reference-----------");
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference ref = new PhantomReference(new MyDate(), queue);
        System.out.println("before gc:" + ref.get());
        System.gc();
        System.out.println("after gc:" + ref.get());
    }

    public static void main(String[] args) {
        weakReference();
        phantomReference();
    }
}
