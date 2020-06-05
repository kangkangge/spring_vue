package com.tbc.demo.catalog.proxy;

import com.tbc.demo.catalog.proxy.staticc.EntityInterface;

public class Entity implements EntityInterface {
    @Override
    public void save() {
        System.out.println("保存成功!");
    }
}
