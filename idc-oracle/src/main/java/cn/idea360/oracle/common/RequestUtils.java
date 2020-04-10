package cn.idea360.oracle.common;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static String obtainParameter(HttpServletRequest request, String parameter) {
        String result =  request.getParameter(parameter);
        return result == null ? "" : result;
    }
}
