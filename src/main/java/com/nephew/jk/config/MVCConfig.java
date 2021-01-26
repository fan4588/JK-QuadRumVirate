package com.nephew.jk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/** spring boot 拦截器执行两次解决方案
 * https://blog.csdn.net/qq_37059838/article/details/88040608?utm_medium
 * =distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2
 * -2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-b
 * log-BlogCommendFromMachineLearnPai2-2.channel_param
 */
@Configuration
public class MVCConfig extends WebMvcConfigurationSupport {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        InterceptorRegistration registration = registry.addInterceptor(securityInterceptor);
        //排除的路径
        registration.excludePathPatterns("/login");
        registration.excludePathPatterns("/logout");
        //将这个controller放行
        registration.excludePathPatterns("/error");
        //拦截全部
        registration.addPathPatterns("/**");
    }


}
