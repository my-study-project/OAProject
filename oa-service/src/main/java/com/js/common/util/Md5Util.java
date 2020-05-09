package com.js.common.util;

/**
 * @author: 姜爽
 * @date: 2019/12/12 14:33
 * @description: 采用MD5加密解密
 * @version: V1.0
 */
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
@Slf4j
public class Md5Util {
    private Md5Util(){
        throw new IllegalStateException("Md5Util工具异常");
    }
    /***
     * MD5加码 生成32位md5码
     */
    public static String stringToMd5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            log.info("MD5加密过程出现异常",e);
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMd5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }
}
