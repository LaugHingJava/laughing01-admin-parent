package com.laughing.crowd.util;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;

//判断请求类型工具类
public class CrowdReqUtil {
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
}
