package com.tbc.demo.catalog.extends_test;

import java.util.ArrayList;
import java.util.List;

public abstract class A {
    private String name;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("995");
        objects.add("994");
        objects.add("993");
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
