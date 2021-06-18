package com.tbc.demo.catalog.ssologin;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static sun.security.pkcs11.wrapper.Functions.toHexString;

public class SSOLogin {


    public static void main(String[] args) {
        String s = calculateSign(action, secret, corpCode, userName, l);
        String sign = baseUrl.replace("SIGN", s);
        System.out.println(sign);
    }


    private static String userName = "admin";
    private static String corpCode = "yingcaibang";
    private static String secret = "yingcaibangtbc123";
    private static String action = "sso";
    private static String host = "http://v4.21tb.com";
    private static String encodeHost;

    static {
        try {
            encodeHost = URLEncoder.encode(host, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static long l = System.currentTimeMillis();
    private static String baseUrl = host + "/wx/elpSSO/ssoLogin.do?userName=" + userName + "&timestamp=" + l + "&corpCode=" + corpCode + "&sign=SIGN&returnUrl=" + encodeHost + "%2FwxIndex%2F%23%2FcustomHome";


    public static String calculateSign(String action, String secret, String corpCode, String usernName, Long timestamp) {
        String signingText = secret + "|" + action + "|" + corpCode + "|" + usernName + "|" + timestamp + "|" + secret;
        return md5(signingText);
    }

    public static String md5(String input) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var4) {
            var4.printStackTrace();
        }

        try {
            md.update(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        byte[] byteDigest = md.digest();
        return toHexString(byteDigest);
    }

}
