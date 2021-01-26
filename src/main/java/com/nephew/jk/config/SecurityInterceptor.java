package com.nephew.jk.config;

import com.nephew.jk.manager.annotation.LoginVerify;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/***
 * 拦截器
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //解决拦截俩次的问题
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            //接口请求校验
            LoginVerify loginVerify = AnnotationUtils.getAnnotation(method, LoginVerify.class);
            if (loginVerify == null) {
                loginVerify = method.getDeclaringClass().getAnnotation(LoginVerify.class);
            }
            if (loginVerify == null) {
                throw new Exception("权限不足");
            }
            String value = loginVerify.value();
            boolean verify = loginVerify.isVerify();
            if (StringUtils.isBlank(value) || !verify) {
                throw new Exception("权限不正确");
            }
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
