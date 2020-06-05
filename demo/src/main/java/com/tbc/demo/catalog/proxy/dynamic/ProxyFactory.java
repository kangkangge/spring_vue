package com.tbc.demo.catalog.proxy.dynamic;

import com.tbc.demo.catalog.proxy.Entity;
import com.tbc.demo.catalog.proxy.staticc.EntityInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Handler;

import static javafx.scene.input.KeyCode.H;

public class ProxyFactory {
    private static Entity entity;
    private static Entity instenceEntity;

    public static void main(String[] args) {
        Proxy.newProxyInstance(entity.getClass().getClassLoader(), Entity.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("");
                return null;
            }
        });
    }
}
