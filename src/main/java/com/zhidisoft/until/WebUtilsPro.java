package com.zhidisoft.until;

import javax.servlet.http.HttpServletRequest;

/**  
 *用于判断请求类型
* @author tkc  
* @date 2018年9月16日  
*/ 
public class WebUtilsPro {

    /**
     * 用于判断是否是ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}

