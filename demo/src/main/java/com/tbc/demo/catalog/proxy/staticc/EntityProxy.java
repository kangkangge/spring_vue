package com.tbc.demo.catalog.proxy.staticc;

import com.tbc.demo.catalog.proxy.Entity;

public class EntityProxy implements EntityInterface {

    private Entity entity;

    public EntityProxy(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void save() {
        System.out.println("开始了呢");
        entity.save();
        System.out.println("结束了呢");
    }
}
