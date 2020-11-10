package com.tbc.demo.catalog.ucloud;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * User: gkk
 * Date: 2020年9月27日17:12:25
 */
@Data
public class ImTemplateUsms implements Serializable {
    private static final long serialVersionUID = 1L;
    //id
    private String id;
    //name
    private String name;
    //模板类型
    private String smsTemplateType;
    //模板id值
    private String contentMd5;
    //运营商提供模板id
    private String smsId;
    //模板参数顺序
    private String paramOrder;
    //模板内容
    private String content;
    //模板注册状态
    private String smsTemplateStatus;
    //模板注册错误信息
    private String smsTemplateErrorMsg;

    private String corpCode;
    private Date createTime;
    private Long optTime;
    private Date lastModifyTime;
    private String createBy;
    private String lastModifyBy;
}