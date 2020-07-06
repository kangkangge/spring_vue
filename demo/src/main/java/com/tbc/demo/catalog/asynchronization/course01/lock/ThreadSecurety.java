package com.tbc.demo.catalog.asynchronization.course01.lock;

import com.tbc.demo.catalog.asynchronization.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.Thread.sleep;

/**
 * 线程安全问题:9.
 */
@Slf4j
public class ThreadSecurety {


    /**
     * 1. 对象在内存内的格式
     * <p>
     * 1.对象头
     * 数据结构
     * 1，Mark Word:
     * 1.对象hashCode信息
     * 2.对象的锁状态标识
     * 3.GC标识
     * 2，指向类的指针 :类型指针指向对象的类元数据
     * 3，数组长度（只有数组对象才有）
     * 2.实例数据
     * 对象实际的值
     * 3.对齐填充
     * jvm要求java内存必须是8bit的倍数
     * <p>
     * 11:14:00.499 [main] INFO com.ddmm.asynchronization.course02.ThreadSecurety - com.ddmm.asynchronization.course02.User object internals:
     * OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
     * ---------------------- mark word 记录对象hash值和锁状态等信息 随着锁状态变化而改变 --------------------------------------
     * 0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * ---------------------- 类的指针用来判断当前对象属于哪一个类 --------------------------------------
     * 8     4                    (object header)                           1c 57 01 f8 (00011100 01010111 00000001 11111000) (-134129892)
     * ---------------------- 对象的实例属性(对象的数据 ) --------------------------------------
     * 12     4                int User.sex                                  1
     * 16     4                int User.age                                  1
     * 20     4   java.lang.String User.naeme                                (object)
     * ---------------------- mark word 记录对象hash值 锁状态信息 --------------------------------------
     * Instance size: 24 bytes
     * ---------------------- 记录填充信息,当字节不是8的倍数的时候会自动填充一部分数据 --------------------------------------
     * Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
     */
    @Test
    public void markWork() throws InterruptedException {
        User user = User.builder().age(11).sex(true).price(22.2).build();
        log.info(ClassLayout.parseInstance(user).toPrintable());
    }


    /**
     * 1.锁的状态变化
     */
    @Test
    public void testLock() {
        Object obj = new Object();
        log.info("初始状态:" + ClassLayout.parseInstance(obj).toPrintable());
        synchronized (obj) {
            log.info("被持有状态" + ClassLayout.parseInstance(obj).toPrintable());
        }
        log.info("释放状态" + ClassLayout.parseInstance(obj).toPrintable());

    }

    /**
     * 2. 线程安全问题,由于线程间的内存不同步导致的超卖问题,
     */
    @Test
    public void test1() throws InterruptedException {
        //创建三个线程对象,模拟三个窗口
        SellTickets s1 = new SellTickets("窗口1");
        SellTickets s2 = new SellTickets("窗口2");
        SellTickets s3 = new SellTickets("窗口3");
        //开启线程售票
        s1.start();
        s2.start();
        s3.start();
        sleep(100);
        log.info("main方法...");
    }
}


