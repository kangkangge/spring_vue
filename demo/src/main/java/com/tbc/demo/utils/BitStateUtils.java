package com.tbc.demo.utils;

/**
 * 描述:
 * 位运算工具类
 *
 * @author denghaowei
 * @date 2018-12-13 11:15
 */
public class BitStateUtils {


    /**
     * @param states
     *            所有状态值
     * @param value
     *            需要判断状态值
     * @return 是否存在
     */
    public static boolean hasState(long states, long value) {
        return (states & value) != 0;
    }

    /**
     * @param states
     *            已有状态值
     * @param value
     *            需要添加状态值
     * @return 新的状态值
     */
    public static long addState(long states, long value) {
        if (hasState(states, value)) {
            return states;
        }
        return (states | value);
    }

    /**
     * @param states
     *            已有状态值
     * @param value
     *            需要删除状态值
     * @return 新的状态值
     */
    public static long removeState(long states, long value) {
        if (!hasState(states, value)) {
            return states;
        }
        return states ^ value;
    }
}
