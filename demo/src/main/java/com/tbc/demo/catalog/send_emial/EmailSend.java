package com.tbc.demo.catalog.send_emial;
import com.tbc.demo.catalog.thread_pool.ThreadPoolExecutorPolicy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.mail.Authenticator;


/**
 * 注释
 *
 * @author gekangkang
 * @date 2019/10/29 18:08
 */
public class EmailSend {

    @Test
    public void sendEmail(){
        for (int i = 0; i < 10000; i++) {
            try {
                System.out.println(i);
                Email email = new SimpleEmail();
                email.setHostName("email.simcere.com");
                //email.setAuthentication("544535975@qq.com","jhadngxbkjedbbah");//z
                email.setAuthentication("JS180047@simcere.com","01Fine09");//z
                //email.setAuthentication("544535975@qq.com","kmoakombqdxdbbjc");//z
                //设置编码格式，防止乱码
                email.setCharset("UTF-8");
                email.setFrom("JS180047@simcere.com");
                email.setSubject("这个是用来测试的");
                email.setMsg("测试");
                email.addTo("112125@testq.com");
                String send = email.send();
            } catch (EmailException e) {
                System.out.println(e.getCause().getMessage());
                e.printStackTrace();
            }
        }
    }

}
