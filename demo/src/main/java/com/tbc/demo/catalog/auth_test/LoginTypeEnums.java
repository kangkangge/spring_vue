package com.tbc.demo.catalog.auth_test;

public enum LoginTypeEnums {
    WX_LOGIN(1, "wx_Login"),
    QYWX_LOGIN(2, "qywx_Login"),
    DD_LOGIN(3, "dd_Login");

    private int code;
    private String msg;

    LoginTypeEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
