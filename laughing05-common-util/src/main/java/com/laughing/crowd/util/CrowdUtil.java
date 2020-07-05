package com.laughing.crowd.util;

import com.laughing.crowd.constant.CrowdConstant;


import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.laughing.crowd.constant.CrowdConstant.MESSAGE_STRING_INVALIDATE;

//工具类
public class CrowdUtil {
    /**判断是否是ajax请求
     * @param httpServletRequest
     * @return
     *  true就是ajax
     *  false就是普通请求
     */
    public static boolean judgeRequestType(HttpServletRequest httpServletRequest){

        String  acceptHeader = httpServletRequest.getHeader("Accept");
        String xRequestHeader = httpServletRequest.getHeader("X-Requested-With");
        if((acceptHeader!=null && acceptHeader.contains("application/json"))||
                ( xRequestHeader!=null&&xRequestHeader.equals("XMLHttpRequest"))){
            return true;
        }
        return false;

    }

    /**把字符串转换成MD5
     * @param source
     * @return
     */
    public static String Md5(String source){
        if(source==null&&source.length()==0){
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        String algorithm="md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            // 5.执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7.按照16 进制将bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
