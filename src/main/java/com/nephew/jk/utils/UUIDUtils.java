package com.nephew.jk.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 生成32位UUID
 */
@Component
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getUUIDReplace() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
