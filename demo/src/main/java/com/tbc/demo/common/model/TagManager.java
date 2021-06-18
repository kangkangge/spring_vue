package com.tbc.demo.common.model;


import lombok.Data;

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

@Data
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

    private String tagCategreName;

    private List<TagManager> subTag;
}