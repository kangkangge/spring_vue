package com.tbc.demo.catalog.algorithm;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class MaoPao {
    public static void main(String[] args) {
        int[] sort = sort(99, 22, 23, 54, 22, 11, 23, 2, 3, 45);
        System.out.println(JSONObject.toJSONString(sort));
    }

    private static int[] sort(int... arr) {
        for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
