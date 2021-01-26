package com.nephew.jk.controller;

import com.nephew.jk.domain.ConfigList;
import com.nephew.jk.domain.ConfigMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api("初始化")
public class ReadConfigController implements InitializingBean {


    @Autowired
    private ConfigList configList;

    @Autowired
    private ConfigMap configMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(configList);
        List<String> limitSizeList = configList.getLimitSizeList();
        String type = "baidu";
        Map<String, Object> companyMap = configMap.getLimitSizeMap();
        Map<String, String> o = (Map<String, String>) companyMap.get(type);
        String a = o.get("a");
        System.out.println(configMap);
        for (Map.Entry<String, Object> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey());
            Map<String, String> value = (Map<String, String>) entry.getValue();
            System.out.println(value.get("b"));
        }
    }
}
