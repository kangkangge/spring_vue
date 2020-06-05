package com.tbc.bean;

import com.tbc.exception.base.CustomEx;
import lombok.Data;

/**
 * @author gkk
 * @date 2019/8/3 17:19
 * @describe 通用返回
 * @version 1.0
 */
@Data
public class JsonResult {
    /**
     * 消息
     */
    private String message;
    /**
     * 返回的数据
     */
    private Object data;

    /**
     * 返回错误时的异常信息
     */
    private Exception ex;

    private boolean success;

    private int code;

    public static JsonResult success() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    public static JsonResult success(String msg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        jsonResult.setMessage(msg);
        return jsonResult;
    }

    public static JsonResult success(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult success(String msg, int code) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        jsonResult.setMessage(msg);
        jsonResult.setCode(code);
        return jsonResult;
    }

    public static JsonResult success(String msg, Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        jsonResult.setData(data);
        jsonResult.setMessage(msg);
        return jsonResult;
    }

    public static JsonResult success(String msg, int code, Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        jsonResult.setData(data);
        jsonResult.setMessage(msg);
        jsonResult.setCode(code);
        return jsonResult;
    }

    public static JsonResult failure() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    public static JsonResult failure(String mes) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(mes);
        return jsonResult;
    }

    public static JsonResult failure(String msg, int code) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(msg);
        jsonResult.setCode(code);
        return jsonResult;
    }

    public static JsonResult failure(String msg, int code, Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(msg);
        jsonResult.setCode(code);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult failure(CustomEx ex) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(ex.causeBy);
        jsonResult.setEx(ex);
        return jsonResult;
    }

}