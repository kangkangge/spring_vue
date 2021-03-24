package com.tbc.demo.catalog.ucloud;

import cn.hutool.core.util.StrUtil;
import cn.ucloud.common.pojo.Account;
import cn.ucloud.usms.client.DefaultUSMSClient;
import cn.ucloud.usms.model.*;
import cn.ucloud.usms.pojo.USMSConfig;
import com.alibaba.fastjson.JSONObject;
import com.tbc.demo.catalog.jedis.jedis;
import com.tbc.demo.utils.MD5Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RegExUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 源代码测试
 */
@Slf4j
public class SourceCodeTest {

    private static USMSConfig usmsconfig = new USMSConfig(new Account(Config.privateKey, Config.publicKey));
    private static DefaultUSMSClient client = new DefaultUSMSClient(usmsconfig);
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    static String PHONE_CONTENT = "尊敬的${TIP_TARGET!}：目前${SYS_SENDER_NAME!}上有新的调查问卷需要您去审核，请您尽快处理，以方便学员参加调查。提示对象：先到达一级节点审核人；";
    static String SMS_PHONE_CONTENT = "尊敬的@TIP_TARGET@：您所创建的知识@KNOWLEDGE_NAME@已审核通过,知识已启用。";
    static String PARA_LIST = "{\"DATE\":\"2020-09-25\",\"OPTION_NAME\":\"admins\",\"LINK_ADDR\":\"nullgkk\",\"TIP_TARGET\":\"gkk\",\"TIME\":\"2020-09-25 10-46-30\",\"USER_NAME\":\"gkk\",\"SECURITY_CODE\":\"594833\",\"SENDER_USER\":\"admins\",\"LOGIN_NAME\":\"gkk\",\"OPTION_TIME\":\"2020-09-25 10-46-30\"}";


    //模板参数顺序
    private static final String PARAMETER_ORDER = "PARAMETER_ORDER";
    //模板参数顺序
    private static final String CONTENT_MD5_ID = "CONTENT_MD5_ID";
    //模板注册状态
    private static final String REGESTER_STATUS = "REGESTER_STATUS";
    //模板MD5 ID REDIS前缀
    private static final String IM_MSG_USMS_MD5_ID = "IM_MSG_USMS_MD5_ID";
    private static final Integer CALL_INTERFACE_SUCCESS = 0;
    private static final Integer REGISTRATION_STATUS_CHECK_PASS = 2;
    private static final Integer REGISTRATION_STATUS_CHECK_FAIL = 1;


    public static void main(String[] args) {

    }



    /**
     * 创建模板测试
     */
    @Test
    public void createUSMSTemplateTest() {
        String content = "尊敬的${TIP_TARGET!}：目前${SYS_SENDER_NAME!}上有新的调查问卷需要您去审核，请您尽快处理，以方便学员参加调查。提示对象：先到达一级节点审核人；";
        String templateName = "调查通知";
        String type = "";
        createUSMSTemplate(content, templateName, "");
    }

    public void createUSMSTemplate(String content, String templateName, String type) {
        if (StrUtil.hasEmpty(templateName, content)) {
            return;
        }
        //判断是否已经注册过
        String contentMD5 = MD5Generator.getHexMD5(content);
        if (jedis.exists(IM_MSG_USMS_MD5_ID + contentMD5)) {
            log.info("IM_NEW_USMS 模板已经存在");
            return;
        }
        ImTemplateUsms imTemplateUsms = new ImTemplateUsms();
        imTemplateUsms.setCorpCode("jinj");
        imTemplateUsms.setName(templateName);
        imTemplateUsms.setContent(content);
        imTemplateUsms.setContentMd5(MD5Generator.getHexMD5(content));
        //短信类型判断
        type = null != type ? content.contains("验证码") ? "1" : "2" : type;
        try {
            //处理模板参数
            Map<String, String> contentParameter = createContentParameter(content);
            String parameter = contentParameter.get("parameter");
            String smsTemplateContent = contentParameter.get("smsTemplateContent");
            content = StringUtils.isEmpty(smsTemplateContent) ? content : smsTemplateContent;
            imTemplateUsms.setParamOrder(parameter);
            //注册模板
            log.info("IM_NEW_USMS 开始申请模板: 模板类型: [{}] , 模板名称: [{}] , 模板内容: [{}]", Integer.valueOf(type), templateName, content);
            CreateUSMSTemplateParam createUSMSTemplateParam = new CreateUSMSTemplateParam(Integer.valueOf(type), templateName, content);
            log.info("IM_NEW_USMS 模板申请结果: [{}]", createUSMSTemplateParam);
            CreateUSMSTemplateResult usmsTemplate = client.createUSMSTemplate(createUSMSTemplateParam);
            if (usmsTemplate.getRetCode().equals(0)) {
                String templateId = usmsTemplate.getTemplateId();
                imTemplateUsms.setSmsId(templateId);
                imTemplateUsms.setSmsTemplateStatus("1");
                jedis.hset(IM_MSG_USMS_MD5_ID + templateId, CONTENT_MD5_ID, contentMD5);
                jedis.hset(IM_MSG_USMS_MD5_ID + templateId, PARAMETER_ORDER, parameter);
                jedis.hset(IM_MSG_USMS_MD5_ID + templateId, REGESTER_STATUS, "1");
                jedis.set(IM_MSG_USMS_MD5_ID + contentMD5, templateId);
            }
        } catch (Exception e) {
            log.info("IM_NEW_USMS 模板申请异常: [{}]", e.getMessage());
        }
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
        String parameter = stringBuilder.toString().replaceAll("\\|\\|$", "");
        map.put("parameter", parameter);
        map.put("smsTemplateContent", smsTemplateContent);
        return map;
    }

    private void deleteRedis(String templateId) {
        if (StringUtils.hasText(templateId)) {
            String id = jedis.hget(IM_MSG_USMS_MD5_ID + templateId, CONTENT_MD5_ID);
            jedis.del(IM_MSG_USMS_MD5_ID + templateId);
            jedis.del(IM_MSG_USMS_MD5_ID + id);
        }
    }
}
