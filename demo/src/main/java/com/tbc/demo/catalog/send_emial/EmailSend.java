package com.tbc.demo.catalog.send_emial;

import org.apache.commons.mail.*;
import org.junit.Test;

import java.io.File;

/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/10/29 18:08
 */
public class EmailSend {

    @Test
    public void sendEmail() {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.qq.com");
            email.setSslSmtpPort("465");
            email.setSSL(true);
            email.setAuthentication("956673560@qq.com", "ssmrlfdqfohlbeej");//z
            email.setCharset("UTF-8");
            email.setFrom("956673560@qq.com");
            email.setSubject("公司设置测试!");
            email.setMsg("公司设置账号测试");
            email.addTo("gekangkang@21tb.com");
            String send = email.send();
            System.out.println(send);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendAttachmentEmail() {
        try {
            //附件对象
            EmailAttachment emailAttachment = new EmailAttachment();
            //邮件对象
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.qq.com");
            email.setSslSmtpPort("465");
            email.setAuthentication("956673560@qq.com", "ssmrlfdqfohlbeej");//z
            email.setCharset("UTF-8");
            email.setFrom("gekangkang@21tb.com");
            email.setSubject("公司设置测试!");
            email.setMsg("公司设置账号测试");
            email.attach(new File(getClass().getClassLoader().getResource("./static/邮件发送失败列表.xls").getPath()));
            email.addTo("gekangkang@21tb.com");
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }


}
