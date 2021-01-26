package com.nephew.jk.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "test-map") // 不同的配置类，其前缀不能相同
@EnableConfigurationProperties(ConfigMap.class) // 必须标明这个类是允许配置的
public class ConfigMap {

    private Map<String, Object> limitSizeMap = new HashMap<>();

    public Map<String, Object> getLimitSizeMap() {
        return limitSizeMap;
    }

    public void setLimitSizeMap(Map<String, Object> limitSizeMap) {
        this.limitSizeMap = limitSizeMap;
    }

}
