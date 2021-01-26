package com.nephew.jk.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 用于Web的通用封装方法
 */
public class WebUtil {

    private final static String CHARSET_GB2312 = "gb2312";
    private final static String CHARSET_UTF8 = "utf-8";
    private final static String ATTACHMENT_CONTENT = "attachment;filename=";
    private final static String DEFAULT_NAME = "default";
    private final static String MEDIA_TYPES = "MedisTypes";

    private final static String URI_SEPARATOR = "/";
    private final static String REDIRECT_SYMBOL = "redirect:";
    private final static String FORWARD_SYMBOL = "forward:";

    /**
     * 适配htm和json格式的返回
     */
    public static void buildReturn(ModelAndView modelAndView, HttpServletRequest request) {
        if (((List<?>) request.getAttribute(MEDIA_TYPES)).contains(MediaType.APPLICATION_JSON)) {
            modelAndView.setView(new MappingJackson2JsonView());
        }
    }

    /**
     * 构建文件下载的响应头
     */
    public static void buildDownloadResponse(String fileName, HttpServletResponse httpServletResponse) {
        httpServletResponse.setCharacterEncoding(CHARSET_UTF8);

        try {
            httpServletResponse.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION,
                    StringUtils.isNotBlank(fileName)
                            ? ATTACHMENT_CONTENT + URLEncoder.encode(fileName, CHARSET_UTF8)
                            : ATTACHMENT_CONTENT + DEFAULT_NAME
            );
        } catch (UnsupportedEncodingException e) {
            httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_CONTENT + DEFAULT_NAME);
        }

        final String CONTENT_TYPE = "application/octet-stream;charset=" + CHARSET_GB2312;
        httpServletResponse.setContentType(CONTENT_TYPE);
    }

    /**
     * 拼装url的get参数
     */
    public static String concatHttpGetParams(String url, Map<String, Object> paramsMap) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }

        if (paramsMap == null || paramsMap.isEmpty()) {
            return url;
        }

        final String PARAM_INDICATOR = "?";
        final String PARAM_SEPARATOR = "&";
        final String EQUAL_SYMBOL = "=";

        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append(PARAM_INDICATOR);

        final int paramsCount = paramsMap.size();
        int count = 0;
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(EQUAL_SYMBOL).append(entry.getValue().toString());
            count++;

            if (count != paramsCount) {
                stringBuilder.append(PARAM_SEPARATOR);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 拼装重定向页面
     */
    public static String concatRedirectViewName(String viewName) {
        if (StringUtils.isEmpty(viewName)) {
            return StringUtils.EMPTY;
        }

        StringBuilder stringBuilder = new StringBuilder(REDIRECT_SYMBOL);

        if (!viewName.startsWith(URI_SEPARATOR)) {
            stringBuilder.append(URI_SEPARATOR);
        }

        stringBuilder.append(viewName);
        return stringBuilder.toString();
    }

    /**
     * 拼装转发页面
     */
    public static String concatForwardViewName(String viewName) {
        if (StringUtils.isEmpty(viewName)) {
            return StringUtils.EMPTY;
        }

        StringBuilder stringBuilder = new StringBuilder(FORWARD_SYMBOL);

        if (!viewName.startsWith(URI_SEPARATOR)) {
            stringBuilder.append(URI_SEPARATOR);
        }

        stringBuilder.append(viewName);
        return stringBuilder.toString();
    }
}
