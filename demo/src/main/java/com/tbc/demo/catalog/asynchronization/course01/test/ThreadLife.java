package com.tbc.demo.catalog.asynchronization.course01.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.Runner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * 1.线程生命周期,与常用api
 */
@Slf4j
public class ThreadLife {

    private Thread threadA;
    private Thread threadB;


    /**
     * join测试
     * 如果当前某程序为多线程程序，假如存在一个线程A，现在需要插入线程B，并要求线程B先执行完毕，然后再继续执行线程A，
     * 此时可以使用Thread类中的join()方法来完成。这就好比此时读者正在看电视，突然有人上门收水费，读者必须付完水费后才能继续看电视。!
     */
    @Test
    public void joinTest() {
        Thread threadA = new Thread(getRunnable("threadA"));
        Thread threadB = new Thread(getRunnable("threadB"));
        threadB.start();
        threadA.start();

        try {
            //主线程与A线程倒要等B线程结束了才可以执行
            threadB.join();
            threadA.join();
            System.out.println("主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 1.生命周期测试
     * sleep 线程休眠只释放系统资源,不释放锁
     * wait  释放资源释放锁
     */
    @Test
    public void runTest() {
        try {
            Integer num = 9999;
            System.out.println("1.出生状态: 就是线程被创建时处于的状态，在用户使用该线程实例调用start()方法之前线程都处于出生状态");
            Thread thread = getRunRunnable();
            thread.setName("测试线程");
            System.out.println("2.就绪状态: 当用户调用start()方法后，线程处于就绪状态（又被称为可执行状态）；当线程得到系统资源后就进入运行状态。");
            thread.start();
            System.out.println("3.2. 当线程进入执行状态的时候可以调用,这里为了测试休眠与唤醒防止主线程执行过快休眠一下");
            sleep(100);
            //设置线程优先级 1-- 10 之间
            thread.setPriority(9);
            System.out.println("3.3. 当线程进入休眠,等待的状态时可以使用 notify() 方法来唤醒");
            //停止线程,非线程安全已经弃用
            thread.stop();
            System.out.println("7. 主线程运行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 生命周期: 出生,就绪,运行,阻塞,死亡
     *
     * @return
     */
    public Thread getRunRunnable() {
        Runnable task = () -> {
            System.out.println("3.获取到系统资源线程开始执行,这个时候可可根据api操作进入:等待,休眠,死亡,阻塞...状态");
            try {
                synchronized (this) {
                    long l = System.currentTimeMillis();
                    System.out.println("3.1 进入休眠状态,线程在这时候回到了就绪的状态");
                    long L = System.currentTimeMillis() - l;
                    System.out.println("5. 子线程运行完毕,死亡");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000; i++) {
                System.out.println("");
                String threadName = Thread.currentThread().getName();
                System.out.println("Hello " + threadName);
            }

        };
        return new Thread(task);
    }


    /**
     * 创建匿名线程
     *
     * @return
     */
    public static Thread getRunnable(String name) {
        Runnable task = () -> {
            System.out.println("当前执行的线程[" + name + "]:");
        };
        return new Thread(task);
    }


    /**
     * 主线程: main 线程
     * 用户线程: 用户创建的线程不会随着主线程的结束而结束.
     * 守护线程: 当主线程(main线程)与用户线程,执行完毕之后,守护线程无论是否执行完毕都会自动销毁!
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = getTestThread("守护线程!");
        t.setDaemon(true);
        t.start();
        Thread userThread = getRunnable("用户线程");
        userThread.start();
        log.info("主线程运行结束,守护线程自动销毁!");
    }

    public static Thread getTestThread(String name) {
        return new Thread(() -> {
            for (int j = 0; j < 1000; j++) {
                log.info("线程:" + name + "执行次数:" + j);
            }
        });
    }


    /**
     * thread local 线程局部变量: 每个线程操作局部变量,只会对自己的线程有效,对其他的线程是无效的
     */
    @Test
    public void testThreadLocal() throws InterruptedException {
        ThreadLocal<Integer> test = new ThreadLocal<>();
        test.set(99);
        Thread testThread = getTestThread(test);
        sleep(99);
        Thread testThread1 = getTestThread(test);
        testThread.start();
        testThread1.start();
        sleep(99);

    }

    public static Thread getTestThread(ThreadLocal<Integer> num) {
        Integer integer = num.get();
        return new Thread(() -> {
            num.set(integer + 1);
            log.info("当前的值:" + num.get());
        });
    }

    @Test
    public void test() throws InterruptedException {
        Thread printThread = new Thread(getTestThread("线程A"));
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行暂停，输出内容工作停止
        printThread.suspend();
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行恢复，输出内容继续
        printThread.resume();
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        // 将PrintThread进行终止，输出内容停止
        printThread.stop();
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }

}



