package com.tbc.demo.catalog.yinlian;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("huaqin")
@Controller
@Slf4j
public class HuaQin {


    String redirectUrlType = "imallUrl";
    String returnUrl = "http%3A%2F%2Fv4.21tb.com%2Fwx%2Fhtml%2Ffront%2Fkm%2FkmView.do%3FknowledgeId%3D0ba071a4e2c04240a699fca486330b09%26corpCode%3Dhuaqin%26knowledgeCnt%3D16;";

    @Test
    public void test() {
        String urlPrefix = "https://cloud.21tb.com/wx/html/front/index.do#/";

        if (StringUtils.isNotEmpty(returnUrl)) {
            if (returnUrl.contains("#")) {
                String[] urlSplit = StringUtils.split(returnUrl, "#");
                StringBuilder sb = new StringBuilder();
                sb.append(urlSplit[0]).append("&eln_session_id=").append("wxSessionId").append("#").append(urlSplit[1]);
                returnUrl = sb.toString();

                urlPrefix = returnUrl;
            } else {
                urlPrefix = returnUrl + "&eln_session_id=" + "wxSessionId";
            }
        }
        System.out.println(urlPrefix);
    }

    @RequestMapping("sendTest")
    public void sendTest(HttpServletResponse response) throws IOException {
        String url = "http%3A%2F%2Fv4.21tb.com%2Fwx%2Fhtml%2Ffront%2Fkm%2FkmView.do%3FknowledgeId%3D0ba071a4e2c04240a699fca486330b09%26corpCode%3Dhuaqin%26knowledgeCnt%3D16;&eln_session_id=wxSessionId";
        url = URLDecoder.decode(url, "utf-8");
        response.sendRedirect(url);
        return;
    }
}
