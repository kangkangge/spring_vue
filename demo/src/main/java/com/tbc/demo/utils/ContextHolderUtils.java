package com.tbc.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class ContextHolderUtils {

    // -- header 常量定义 --//
    public static final String ENCODING_PREFIX = "encoding";
    public static final String NOCACHE_PREFIX = "no-cache";
    public static final String ENCODING_DEFAULT = "UTF-8";
    public static final boolean NOCACHE_DEFAULT = true;

    // -- content-type 常量定义 --//
    public static final String TEXT_TYPE = "text/plain";
    public static final String JSON_TYPE = "application/json";
    public static final String XML_TYPE = "text/xml";
    public static final String HTML_TYPE = "text/html";
    public static final String JS_TYPE = "text/javascript";
    /**
     * 直接输出内容的简便函数.
     * <p>
     * eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain",
     * "hello", "no-cache:false"); render("text/plain", "hello", "encoding:GBK",
     * "no-cache:false");
     *
     * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
     */
    public static void render(final String contentType, final String content,
                              HttpServletResponse response,final String... headers) {
        try {
            // 分析headers参数
            String encoding = ENCODING_DEFAULT;
            boolean noCache = NOCACHE_DEFAULT;
            for (String header : headers) {
                String headerName = StringUtils.substringBefore(header, ":");
                String headerValue = StringUtils.substringAfter(header, ":");

                if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
                    encoding = headerValue;
                } else if (StringUtils.equalsIgnoreCase(headerName,
                        NOCACHE_PREFIX)) {
                    noCache = Boolean.parseBoolean(headerValue);
                } else
                    throw new IllegalArgumentException(headerName
                            + "不是一个合法的header类型");
            }


            // 设置headers参数
            String fullContentType = contentType + ";charset=" + encoding;
            response.setContentType(fullContentType);
            if (noCache) {
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
            }
            if (log.isDebugEnabled())
                log.debug(content);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                response.getWriter().close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
