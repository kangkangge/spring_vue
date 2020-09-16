package com.tbc.demo.catalog.yinlian;

import lombok.Data;

/**
 * @author Yohann
 */
@Data
public class ImSms {

    // 手机号
    private String phoneNumber;
    // 短信内容
    private String smsContent;
    // 模板id
    private String smsTempId;
    // 短信标签
    private String signTag;
    // corpCode
    private String corpCode;

    private String userId;

    // 错误信息（发送失败时）
    private String error;


    private String referId;

}
