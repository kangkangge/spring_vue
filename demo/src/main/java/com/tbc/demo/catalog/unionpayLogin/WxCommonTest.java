package com.tbc.demo.catalog.unionpayLogin;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class WxCommonTest {


    @org.junit.Test
    public void test() {
        int i = updataOrinsert();
        log.info("更新的结果 : [{}]", i > 0);
    }

    public int updataOrinsert() {
        WxCommonSSOServcie wxCommonSSOServcie = getwxCommonSSOServcie();
        WxCommonSSO sso = getSSO();
        sso.setUserId("4657465464a65s4d6f54");
        sso.setParam("第3次");
        wxCommonSSOServcie.updateByList(Arrays.asList(sso));
        sso.setParam("第3次");
        Map<String, Integer> stringIntegerMap = wxCommonSSOServcie.updateByList(Arrays.asList(sso));
        return 1;
    }


    public int updataTest(String msg, String param) {
        WxCommonSSOServcie wxCommonSSOServcie = getwxCommonSSOServcie();
        WxCommonSSO sso = getSSO();
        sso.setUserId("9c7b5b5d6e7e4eb6a31d690a24698508");
        sso.setOtherUserId(msg);
        sso.setParam(msg);
        sso.setParam2(msg);
        sso.setParam1(msg);
        sso.setParam3(msg);
        return wxCommonSSOServcie.updateByUserId(sso);
    }

    @org.junit.Test
    public void delTest() {
        WxCommonSSOServcie wxCommonSSOServcie = getwxCommonSSOServcie();
        int other = wxCommonSSOServcie.deleteByOtherId("4c1dc795f3a7467dba5e3f44dacf79d4");
        int user = wxCommonSSOServcie.deleteByUserId("dfc285ab484c40678911df5797a71b4e");
        log.info("通过外部id删除结果: [{}] ,通过user id 删除: [{}]", other > 0, user > 0);
    }

    @org.junit.Test
    public void queryTest() {
        WxCommonSSOServcie wxCommonSSOServcie = getwxCommonSSOServcie();
        WxCommonSSO wxCommonSSO = wxCommonSSOServcie.selectByOtherId("4c1dc795f3a7467dba5e3f44dacf79d4");
        WxCommonSSO wxCommonSSO2 = wxCommonSSOServcie.selectByPhone("12336545654");
        WxCommonSSO wxCommonSSO1 = wxCommonSSOServcie.selectByUserId("4c1dc795f3a7467dba5e3f44dacf79d4");
        log.info("通过外部userId 查询结果:[{}] , 通过手机号查询结果 :[{}] , 通过userid查询结果: [{}]", wxCommonSSO != null, wxCommonSSO2 != null, wxCommonSSO1 != null);
    }

    @org.junit.Test
    public void insertTest() {
        WxCommonSSOServcie wxCommonSSOServcie = getwxCommonSSOServcie();
        int insert = wxCommonSSOServcie.insert(getSSO());
        log.info("新增执行结果:[{}]", insert > 0);
    }

    public void insertTest(WxCommonSSOConfigServcie wxCommonSSOConfigServcie) {
        int insert = wxCommonSSOConfigServcie.insert(getConfig());
        log.info("config: 新增测试结果:[{}]", insert > 0);
    }

    public Integer updateTest(WxCommonSSOConfigServcie wxCommonSSOConfigServcie, WxCommonSSOConfig wxCommonSSOConfig, String msg) {
        int insert = wxCommonSSOConfigServcie.insert(getConfig());
        wxCommonSSOConfig.setKey(msg);
        wxCommonSSOConfig.setParam(msg);
        wxCommonSSOConfig.setRemark(msg);
        int i = wxCommonSSOConfigServcie.updateByCorpCode(wxCommonSSOConfig);
        log.info("config: 新增测试结果:[{}]", insert > 0);
        return i;
    }

    public WxCommonSSOConfig queryByCorpCode(WxCommonSSOConfigServcie wxCommonSSOConfigServcie, String corpCode) {
        WxCommonSSOConfig wxCommonSSOConfig = wxCommonSSOConfigServcie.selectByCorpCode(corpCode);
        log.info("config: 查询测试结果:[{}]", wxCommonSSOConfig != null);
        return wxCommonSSOConfig;
    }

    public static JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.postgresql.Driver");
        druidDataSource.setUrl("jdbc:postgresql://172.21.0.18:5432/ps");
        druidDataSource.setPassword("Eln4postgres");
        druidDataSource.setUsername("postgres");
        jdbcTemplate.setDataSource(druidDataSource);
        return jdbcTemplate;
    }

    public static WxCommonSSOServcie getwxCommonSSOServcie() {
        return new WxCommonSSOServcieImpl();
    }


    public WxCommonSSOConfig getConfig() {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date date = new Date();
        WxCommonSSOConfig wxCommonSSOConfig = new WxCommonSSOConfig();
        wxCommonSSOConfig.setId(id);
        wxCommonSSOConfig.setCorpCode("jinj");
        wxCommonSSOConfig.setCreateTime(date);
        wxCommonSSOConfig.setLastModifyTime(date);
        wxCommonSSOConfig.setCreateBy("jkdflkdfdklf");
        wxCommonSSOConfig.setDomain("www.baidu.com");
        wxCommonSSOConfig.setParam("123阿打发斯蒂芬阿斯顿发送到发撒旦法s");
        wxCommonSSOConfig.setParam1("123阿打发斯蒂芬阿斯顿发送到发撒旦法s");
        wxCommonSSOConfig.setParam2("123阿打发斯蒂芬阿斯顿发送到发撒旦法s");
        wxCommonSSOConfig.setKey("134阿斯顿发送到发撒旦法asdfasdf..");
        return wxCommonSSOConfig;
    }

    public WxCommonSSO getSSO() {
        WxCommonSSO wxCommonSSOConfig = new WxCommonSSO();
        String replace = UUID.randomUUID().toString().replace("-", "");
        Date date = new Date();
        wxCommonSSOConfig.setCorpCode("jinj-yufa");
        wxCommonSSOConfig.setUserId(replace);
        wxCommonSSOConfig.setOtherUserId(replace);
        wxCommonSSOConfig.setCreateTime(date);
        wxCommonSSOConfig.setUserId(replace);
        wxCommonSSOConfig.setCreateTime(date);
        wxCommonSSOConfig.setPhone("12336545654");
        wxCommonSSOConfig.setOtherUserId(replace);
        wxCommonSSOConfig.setParam("lkfjaasdfsadf撒旦法");
        return wxCommonSSOConfig;
    }

    public WxCommonSSOConfigServcie getwxCommonSSOConfigServcie() {
        return new WxCommonSSOConfigServcieImpl();
    }

}
