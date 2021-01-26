package com.nephew.jk.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "test-list") // 不同的配置类，其前缀不能相同
@EnableConfigurationProperties(ConfigList.class) // 必须标明这个类是允许配置的
public class ConfigList {

    private List<String> limitSizeList = new ArrayList<>();

    public List<String> getLimitSizeList() {
        return limitSizeList;
    }

    public void setLimitSizeList(List<String> limitSizeList) {
        this.limitSizeList = limitSizeList;
    }
}
