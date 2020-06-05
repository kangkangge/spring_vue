package com.tbc.exception.enums;

/**
 * 异常处理 枚举类
 * 
 * @author gkk
 */
public enum ExCause {

    overtime(2000, "请求超时"),
    badRequest(400, "请求参数不完整"),
    ErrorMessages(200, "自定义错误");

    private int code;
    private String msg;

    //构造器函数参数的顺序要和成员变量参数上下顺序一致!
    ExCause(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    //获取属性重要的方法values();
    public  static String getMsg(int code) {
        for (ExCause cause : values()) {
            if (cause.code == code) {
                return cause.msg;
            }
        }
        return null;
    }
}
