package com.tbc.demo.Enum;

/**
 * @author gkk
 * @date 2019/8/3 18:40
 * @describe
 * @version 1.0
 */
public enum Color {
    /** 枚举的好处
     * 1 信息更完善：不仅可以保存常量的值，也可以保存常量对应的解释，以及其他信息，当业务上需要在后台翻译常量含义时用处很大
     *
     * 2 可进行参数校验：可以遍历某个枚举类型下所有可能的值，利用MessageType.values()可以遍历枚举内的常量
     *
     * 场景举例：前台传递消息类型，后台需要判断消息类型是否有效（是否MessageType内有定义），比如前端传递消息类型是4，我需要判断这个类型和[SYSTEM_MESSAGE,COMMENT_MESSAGE,COLLECT_MESSAGE]的Code是否有相等。
     */
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        //主要在于 values()遍历枚举定义的所有的属性进行对比!
        for ( Color c :  values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}