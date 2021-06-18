package com.tbc.demo.catalog.binary;

import com.tbc.demo.utils.BitStateUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Binary
 * @Description 进制转换
 * @Date 2021/6/16 19:52
 * @Created by gekang
 */
public class Binary {
    /**
     * bitmap用最少的内存记录大量的状态信息
     */
    @Test
    public void Binary() {
        //Long 转二进制
        String s = Long.toBinaryString(99999999999999L);
        System.out.println("Long 转二进制:" + s);
        //二进制转  Long
        Long s1 = Long.valueOf(s, 2);
        System.out.println("二进制转  Long" + s1);
        Long s2 = Long.valueOf("11001", 2);
        long l = BitStateUtils.addState(s2, 1L << 1);
        System.out.println("状态添加前:" + Long.toBinaryString(s2) + "  状态添加后:" + Long.toBinaryString(l));
        long l1 = BitStateUtils.removeState(l, 1L << 1);
        System.out.println("状态删除前:" + Long.toBinaryString(l) + "  状态删除后:" + Long.toBinaryString(l1));
    }

    public static void main(String[] args) {
        Long count = Long.valueOf("1111", 2);
        Long num = Long.valueOf("11011", 2);
        System.out.println(Long.toBinaryString(count & num));
    }

    public static List<Integer> getPutInTypeStateList(Long subjectState) {
        List<Integer> subjectStateList = new ArrayList<>(PutInTypeEnum.values().length);
        if (subjectState == null) {
            return subjectStateList;
        }
        for (PutInTypeEnum subjectStateEnum : PutInTypeEnum.values()) {
            if (BitStateUtils.hasState(subjectState, subjectStateEnum.getValue())) {
                subjectStateList.add(subjectStateEnum.getKey());
            }
        }
        return subjectStateList;
    }

}
