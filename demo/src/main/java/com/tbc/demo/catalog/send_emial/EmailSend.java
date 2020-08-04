package com.tbc.demo.catalog.send_emial;

import com.tbc.demo.catalog.excel.Jxl;
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
            email.setHostName("mail.21tb.com");
//            email.setSSL(true);
//            email.setSslSmtpPort("465");
            email.setAuthentication("gekangkang@21tb.com", "DAXIExiaoxie_=`9987");//z
            email.setCharset("UTF-8");
            email.setFrom("gekangkang@21tb.com");
            email.setSubject("公司设置测试!");
            email.setMsg("公司设置账号测试");
            email.addTo("gekangkang@21tb.com");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    //测试附件邮件发送
    @Test
    public void sendAttachmentEmail() throws Exception {
        try {
            //附件对象
            EmailAttachment emailAttachment = new EmailAttachment();
            //邮件对象
            HtmlEmail email = new HtmlEmail();
            email.setHostName("mail.21tb.com");
            email.setSSL(true);
            email.setSslSmtpPort("465");
            email.setAuthentication("gekangkang@21tb.com", "DAXIExiaoxie_=`9987");//z
            email.setCharset("UTF-8");
            email.setFrom("gekangkang@21tb.com");
            email.setSubject("公司设置测试!");
            email.setMsg("公司设置账号测试");
            email.attach(new Jxl().writeExcel());
            email.addTo("li0-");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }


}
