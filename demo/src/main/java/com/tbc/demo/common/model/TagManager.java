package com.tbc.demo.common.model;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
 Navicat Premium Data Transfer

 Source Server         : 本机测试
 Source Server Type    : MySQL
 Source Server Version : 50549
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50549
 File Encoding         : 65001

 Date: 23/03/2021 17:02:15

SET NAMES utf8mb4;
        SET FOREIGN_KEY_CHECKS = 0;

        -- ----------------------------
        -- Table structure for t_tag_manager
        -- ----------------------------
        DROP TABLE IF EXISTS `t_tag_manager`;
        CREATE TABLE `t_tag_manager`  (
        `tag_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
        `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
        `tag_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
        `corp_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
        `create_by` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
        `create_time` date NULL DEFAULT NULL,
        `last_modify_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
        `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        `opt_time` bigint(20) NULL DEFAULT NULL,
        `use_count` bigint(20) NULL DEFAULT NULL,
        `tag` tinyint(1) NULL DEFAULT NULL,
        `search_count` bigint(20) NULL DEFAULT NULL,
        `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
        `show_order` bigint(20) NULL DEFAULT NULL,
        `tag_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL
        ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

        SET FOREIGN_KEY_CHECKS = 1;


        */

public class TagManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tagId;

    private String parentId;

    private String tagName;

    private String corpCode;

    private Integer searchCount;

    private String status;

    private String createBy;

    private Date createTime;

    private String lastModifyBy;

    private Date lastModifyTime;

    private Long optTime;

    private Long useCount;

    private Integer showOrder;

    private String tagDesc;

    private Boolean tag;

    private List<TagManager> subTag;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getOptTime() {
        return optTime;
    }

    public void setOptTime(Long optTime) {
        this.optTime = optTime;
    }

    public Long getUseCount() {
        return useCount;
    }

    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }

    public List<TagManager> getSubTag() {
        return subTag;
    }

    public void setSubTag(List<TagManager> subTag) {
        this.subTag = subTag;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

}