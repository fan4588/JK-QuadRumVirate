package com.nephew.jk.utils;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * MD5加密工具
 */
public class MD5Encode {
    /**
     * 生成指定字符串的MD5值
     *
     * @param str
     * @return
     */
    public static String encode(String str) throws Exception {
        String result = null;
        result = DigestUtils.md5Hex(str);
        if(StringUtils.isEmpty(result)) throw new Exception("Encryption failure");
        return result;
    }


    public static String encodeWithSalt(String str, String salt) throws Exception {
        if(StringUtils.isEmpty(str)){
            return null;
        }
        String result_1 = null;
        String result_2 = null;
        result_1 = encode(str);
        result_2 = DigestUtils.md5Hex(result_1+salt);
        if(StringUtils.isEmpty(result_2)) throw new Exception("Encryption failure");
        return result_2;
    }


    public static void main(String[] args) throws Exception {
        //System.out.println(encodeWithSalt("123456","40281e815cef4f99015cef71f1850007"));
        System.out.println(encode(""));
    }

}
