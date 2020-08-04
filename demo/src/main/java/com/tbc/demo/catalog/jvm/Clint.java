package com.tbc.demo.catalog.jvm;

/**
 * 类加载的时候如果存在静态代码块或者静态变量会收集这些数据,生成一个Clinit方法,对这些参数进行初始化
 */
public class Clint {
    static {
        i = 0;
       // System.out.println(i);//Clint方法在这个时候还没有收集到i定义的数据
    }
    static int i = 21;
}