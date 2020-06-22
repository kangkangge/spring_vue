package com.tbc.demo.catalog.send_emial;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;
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
            email.setSmtpPort(25);
            //email.setAuthentication("544535975@qq.com","jhadngxbkjedbbah");//z
            email.setAuthentication("956673560@qq.com", "ssmrlfdqfohlbeej");//z
            //email.setAuthentication("544535975@qq.com","kmoakombqdxdbbjc");//z
            //设置编码格式，防止乱码
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

}
