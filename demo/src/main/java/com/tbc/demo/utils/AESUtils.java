package com.tbc.demo.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.util.UUID;

public class AESUtils {
    private static final String algorithmStr = "AES/ECB/PKCS5Padding";
    private static final Object TAG = "AES";
    private static KeyGenerator keyGen;
    private static Cipher cipher;
    private static boolean isInited = false;

    public AESUtils() {
    }

    private static void init() {
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        keyGen.init(128);

        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception var1) {
            var1.printStackTrace();
        }

        isInited = true;
    }

    private static byte[] genKey() {
        if (!isInited) {
            init();
        }

        return keyGen.generateKey().getEncoded();
    }

    private static byte[] encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText = null;
        if (!isInited) {
            init();
        }

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        try {
            cipher.init(1, key);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        try {
            encryptedText = cipher.doFinal(content);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return encryptedText;
    }

    private static byte[] encrypt(String content, String password) throws Exception {
        if (content == null) {
            return null;
        } else {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(1, key);
            return cipher.doFinal(byteContent);
        }
    }

    private static byte[] decrypt(byte[] content, String password) throws Exception {
        if (content == null) {
            return null;
        } else {
            byte[] keyStr = getKey(password);
            SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, key);
            return cipher.doFinal(content);
        }
    }

    private static byte[] getKey(String password) {
        byte[] rByte;
        if (password != null) {
            rByte = password.getBytes();
        } else {
            rByte = new byte[24];
        }

        return rByte;
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) throws Exception {
        byte[] result = new byte[hexStr.length() / 2];

        for (int i = 0; i < hexStr.length() / 2; ++i) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }

    public static String encode(String content, String secretKey) throws Exception {
        return parseByte2HexStr(encrypt(content, secretKey));
    }

    public static String decode(String content, String secretKey) throws Exception {
        if (content == null) {
            return null;
        } else {
            byte[] contentBytes = parseHexStr2Byte(content);
            byte[] b = decrypt(contentBytes, secretKey);
            return b == null ? null : new String(b);
        }
    }


    public static String getKay() {
        //注意不同环境会有不同的秘钥长度要求,已知16位 24 位
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    /**
     *
     * @param telphone 手机号
     * @param key 约定的秘钥
     * @return
     * @throws Exception
     */
    public static String encodeAddtimestamp(String telphone, String key) throws Exception {
        return encode(telphone + "_" + System.currentTimeMillis(), key);
    }

    public static void main(String[] args) throws Exception {
        String encode = encodeAddtimestamp("13777777777", "b6115be355b44799");
        System.out.println("加密: " + encode);
        String decode = decode(encode, "b6115be355b44799");
        System.out.println(decode);
    }

}