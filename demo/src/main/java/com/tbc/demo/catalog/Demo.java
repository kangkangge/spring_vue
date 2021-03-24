package com.tbc.demo.catalog;

import cn.hutool.core.util.StrUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tbc.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.embedded.netty.NettyWebServer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterCommand;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Demo {
    static String from = "\t尊敬的${TIP_TARGET!}：网院提醒您，您有新的课程需要学习，详情如下：\n" +
            "\t＜#list planCourseSchemeList as courseScheme＞\n" +
            "\t课程类型：${courseScheme.STUDY_TYPE!}，课程编号：${courseScheme.COURSE_CODE!}，课程名称：${courseScheme.COURSE_NAME!}，期限：${courseScheme.STUDY_LIMIT_TIME!}，请尽快去学习${courseScheme.SYMBOL!}\n" +
            "\t＜/#list＞";
    static String content = "尊敬的{1}}：网院提醒您，您有新的课程需要学习，详情如下：课程类型：{2}}，课程编号：{3}}，课程名称：{4}}，期限：{5}}，请尽快去学习{6}}";
    static String messageContent = "@TIP_TARGET@=gkk,@EXAM_CODE@=20201109135325278,@EXAM_NAME@=测试考试下发消息提醒,@EXAM_START_TIME@=2020-11-0913:55:33,@EXAM_END_TIME@=2020-11-2813:53:41,@EXAM_TIME_LENGTH@=60";
    static String parame = "TIP_TARGET||EXAM_CODE||EXAM_NAME||EXAM_START_TIME||EXAM_END_TIME||EXAM_TIME_LENGTH";

    public static void main(String[] args) throws Exception {
        String[] split = parame.split("[\\|]{2}");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            String s1 = "@" + s + "@=";
            Matcher matcher1 = Pattern.compile(s1 + ".*?(@|$)").matcher(messageContent);
            matcher1.find();
            String replace = matcher1.group().replaceAll(s1 + "|@|", "");
            list.add(replace);
        }
        System.out.println(list);
    }


    public void test() {
        StringBuilder stringBuilder = new StringBuilder();
        String s = stringBuilder.toString().replaceFirst("111", "");
        System.out.println(s);
    }

    private static Map<String, String> createContentParameter(String smsTemplateContent) {
        if (org.apache.commons.lang3.StringUtils.isBlank(smsTemplateContent)) {
            return null;
        }
        //匹配 @xxx@ 格式
        String regex = "@[a-zA-Z]+([_\\.][a-zA-Z]+){0,3}@";
        Matcher matcher = Pattern.compile(regex).matcher(smsTemplateContent);
        boolean matches = matcher.find();
        if (!matches) {
            regex = "[${]{2}[a-zA-Z]+([_\\.][a-zA-Z]+){0,3}[!}]{2}";
        }
        matcher = Pattern.compile(regex).matcher(smsTemplateContent);
        Map<String, String> map = new HashMap<>();
        Integer i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            String parameter = matcher.group().trim();
            smsTemplateContent = smsTemplateContent.replace(parameter, "{" + i + "}");
            //去除 首尾的 @符号
            if (matches) {
                parameter = parameter.substring(1, parameter.length() - 1).trim();
            } else {
                parameter = parameter.substring(2, parameter.length() - 2).trim();
            }
            stringBuilder.append(parameter).append("||");
            i += 1;
        }
        String string = stringBuilder.toString();
        String replace = string.replace("||^", "");
        map.put("parameter", replace);
        map.put("smsTemplateContent", smsTemplateContent);
        return map;
    }


    public static List<String> getDiff(String main, String diff) {
        if (StrUtil.isEmpty(diff)) {
            return null;
        }
        if (StrUtil.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }
        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if (StrUtil.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }

}
