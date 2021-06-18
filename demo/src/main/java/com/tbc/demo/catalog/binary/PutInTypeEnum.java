package com.tbc.demo.catalog.binary;

import com.tbc.demo.utils.BitStateUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 名单来源方式
 */
public enum PutInTypeEnum {
    NEW_ADD_STUDENT(1, 1L << 0, "自主新增"),
    PUT_IN_DATABASE(2, 1L << 1, "老名单怼库"),
    MARKET_LAUNCH(3, 1L << 2, "市场投放"),
    BUY_LESSONS(4, 1L << 3, "购课"),
    TRANSFER_INTRODUCTION(5, 1L << 4, "转介绍"),
    LOOK_FOR_BACK(6, 1L << 5, "寻回"),
    SYSTEM_ALLOCATE(7, 1L << 6, "系统分配");

    private Integer key;
    private Long value;
    private String subject;

    PutInTypeEnum(Integer key, Long value, String subject) {
        this.key = key;
        this.value = value;
        this.subject = subject;
    }

    public Integer getKey() {
        return key;
    }

    public Long getValue() {
        return value;
    }

    public String getSubject() {
        return subject;
    }

    /**
     * 获取选中入库类型状态值
     *
     * @param keyList
     * @return
     */
    public static Long getPutInTypeState(List<Integer> keyList) {
        if (keyList == null) {
            return 0L;
        }
        long valueSum = 0L;
        for (PutInTypeEnum subjectStateEnum : values()) {
            if (keyList.contains(subjectStateEnum.key)) {
                valueSum = BitStateUtils.addState(valueSum, subjectStateEnum.value);
            }
        }
        return valueSum;
    }

    /**
     * 获取选中入库状态值1L << 1
     *
     * @param key
     * @return 位的值
     */
    public static Long getPutInTypeState(Integer key) {
        long valueSum = 0L;
        if (key == null) {
            return valueSum;
        }
        for (PutInTypeEnum subjectStateEnum : values()) {
            if (key.equals(subjectStateEnum.key)) {
                valueSum = BitStateUtils.addState(valueSum, subjectStateEnum.value);
            }
        }
        return valueSum;
    }

    /**
     * 根据状态值获取选中的入库方式
     * 如传入3  返回 [1,2] 也就是  1和2两种入库类型
     *
     * @param subjectState
     * @return
     */
    public static List<Integer> getPutInTypeStateList(Long subjectState) {
        List<Integer> subjectStateList = new ArrayList<>(values().length);
        if (subjectState == null) {
            return subjectStateList;
        }
        for (PutInTypeEnum subjectStateEnum : values()) {
            if (BitStateUtils.hasState(subjectState, subjectStateEnum.value)) {
                subjectStateList.add(subjectStateEnum.key);
            }
        }
        return subjectStateList;
    }

    /**
     * 根据学科id获取状态值
     *
     * @param key
     * @return
     */
    public static Long getPutInTypeValueByKey(Integer key) {
        if (key == null) {
            return 0L;
        }
        for (PutInTypeEnum subjectStateEnum : values()) {
            if (subjectStateEnum.key.equals(key)) {
                return subjectStateEnum.value;
            }
        }
        return 0L;
    }

    /**
     * 根据状态值获取选中的学科
     *
     * @param subjectState
     * @return
     */
    public static String getPutInTypeNameList(Long subjectState) {
        if (subjectState == null) {
            return StringUtils.EMPTY;
        }
        StringBuffer subjectName = new StringBuffer();
        int i = 0;
        for (PutInTypeEnum subjectStateEnum : values()) {
            if (BitStateUtils.hasState(subjectState, subjectStateEnum.value)) {
                subjectName.append(subjectStateEnum.subject).append(", ");
                i++;
            }
        }
        if (i == values().length) {
            subjectName = new StringBuffer("全部");
        }
        return subjectName.length() < 3 ? subjectName.toString() : subjectName.substring(0, subjectName.length() - 2);
    }

}
