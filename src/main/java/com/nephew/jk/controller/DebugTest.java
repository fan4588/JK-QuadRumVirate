package com.nephew.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * 测试控制类
 */
@Controller
public class DebugTest {

    public static void main(String[] args) {

        for (int i = 0; i<=10 ;i++){
            int b = 0;
            System.out.println(i+b);
        }

    }

    @GetMapping("zxc")
    @ResponseBody
    public String aaa(String a){
        /*XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(request);
        String parameter = xssHttpServletRequestWrapper.getParameter("a");
        String[] parameterValues = xssHttpServletRequestWrapper.getParameterValues("a");
        Map<String, String[]> parameterMap = xssHttpServletRequestWrapper.getParameterMap();
        System.out.println(parameter);
        System.out.println(StringUtils.join(parameterValues, ","));
        System.out.println(JSONObject.toJSONString(parameterMap));*/
        System.out.println("------------");
        System.out.println(a);
        return a;
    }

}
