package com.tbc.demo.catalog.jvm;

import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * java常见溢出情况模拟
 */
public class OOM {

    /**
     * java堆溢出 ,如果 堆中的内存不够存储对象的数据就会溢出
     * 测试使用jvm参数：
     * `          `-Xms20m  (java 堆初始化空间为20 M)-Xmx20m  (java 堆最大空间为20M)-XX:+HeapDumpOnOutOfMemoryError
     * 错误信息:
     * `         `java.lang.OutOfMemoryError: Java heap space
     * `         `Dumping heap to java_pid15080.hprof ...
     * `         `Heap dump file created [29827088 bytes in 0.133 secs]
     */
    @Test
    public void HeapOOM() {
        List list = new ArrayList();
        while (true) {
            list.add(new OOM());
        }
    }

    /**
     * 虚拟机栈溢出 :
     * `     `1.无论是由于栈帧太大还是虚拟机栈容量太小，当新的栈帧内存无法分配的时候，HotSpot虚拟机抛出的都是StackOverflowError异常。。
     * <p>
     * `     `2.如果虚拟机的栈内存允许动态扩展，当扩展栈容量无法申请到足够的内存时，将抛出OutOfMemoryError异常。
     * 测试使用jvm参数：
     * `     `-Xss128k
     * 错误信息:
     * `     ` StackOverflowError 因为循环调用导致jvm的方法栈内存无限的向java虚拟机栈中压栈导致溢出
     */
    @Test
    public void stackOOM() {
        stackOOMDemo();
    }

    public void stackOOMDemo() {
        stackOOM();
    }

    /**
     * 方法区和运行时常量池溢出:
     * `        `在jdk1.7之后通过该方法是无法重现的,因为JDK1.8 开始永久代已经不再使用
     * 测试使用jvm参数:
     * `        ` -XX:MetaspaceSize=7M -XX:MaxMetaspaceSize=7M
     */
    @Test
    public void RuntimeConstantPoolOOM() {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            //String::intern()是一个本地方法，它的作用是如果字符串常量池中已经包含一个等于此String对象的字符串，
            // 则返回代表池中这个字符串的String对象的引用；否则，会将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用。
            set.add(String.valueOf(i++).intern());
            System.out.println(String.valueOf(i++).intern());
        }
    }

    /**
     * 直接内存溢出:
     * `        ` 直接内存默认与java堆内存你大小一直,可以通过 XX：MaxDirectMemorySize 来指定 ,DirectMemory（典型的间接使用就是NIO），那就可以考虑重点检查一下直接内存方面的原因了。
     * jvm 参数设置:
     * `        ` -Xmx20M -XX:MaxDirectMemorySize=10M
     */
    @Test
    public void directMemoryOOM() throws IllegalAccessException {
        final int _1MB = 1024 * 1024;
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
