package com.tbc.demo.catalog.javaagent;

import java.lang.instrument.Instrumentation;

public class PremainTest {

    public static void premain(String[] agentOps ,Instrumentation inst) {
        System.out.println("====premain 方法执行");
        System.out.println(agentOps);
    }
}
