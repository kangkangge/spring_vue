package com.tbc.demo.catalog.response_standard.common_base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public Result(Resultcode resultCode, String data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public void setCode(Resultcode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
/*
    //回成功
    public static Result success() {
        //返回成功
        Result result = new Result();
        result.setCode(Resultcode.SUCCESS);
        return result;
    }

    //回成功
    public static Result success(Object obj) {
        //返回成功
        Result result = new Result();
        result.setCode(Resultcode.SUCCESS);
        result.setData(obj);
        return result;
    }

    //返回失败
    public static Result failure(Resultcode resultcode) {
        Result result = new Result();
        result.setCode(resultcode);
        return result;
    }

    //返回失败
    public static Result failure(Resultcode resultCode, Object data) {
        Result result = new Result();
        result.setCode(resultCode);
        result.setData(data);
        return result;
    }*/
}
