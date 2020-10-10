package com.nephew.jk.manager;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring容器的获取方案
 */
@Component
public class ApplicationContextManager implements ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }
}
