package com.nephew.jk.constant;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 请求返回数据
 * 200 - 成功
 * 400 - 失败（bad request）
 * 401 - 未登录
 * 403 - 无权限
 */
public class Result extends HashMap<String, Object> {

    /**
     * 返回状态（使用的http状态码HttpServletResponse中获取）
     */
    private static final String KEY_STATUS = "status";
    /**
     * 返回信息code码（自定义异常code码）
     */
    private static final String KEY_CODE = "code";
    /**
     * 返回信息
     */
    private static final String KEY_MESSAGE = "message";
    /**
     * 返回数据
     */
    private static final String KEY_DATA = "data";

    public Result() {
        init(HttpServletResponse.SC_OK, "请求成功");
    }

    public Result(String message) {
        init(HttpServletResponse.SC_OK, message);
    }

    public Result(int status, String message) {
        init(status, message);
    }

    public Result(Object data, Long count) {
        init(data, count);
    }

    public void setFailed(String message) {
        init(HttpServletResponse.SC_BAD_REQUEST, message);
    }

    public int getStatus() {
        return (int) this.get(KEY_STATUS);
    }

    public void setStatus(int status) {
        this.put(KEY_STATUS, status);
    }

    public String getCode() {
        return (String) this.get(KEY_CODE);
    }

    public void setCode(String code) {
        this.put(KEY_CODE, code);
    }

    public String getMessage() {
        return (String) this.get(KEY_MESSAGE);
    }

    public void setMessage(String message) {
        this.put(KEY_MESSAGE, message);
    }

    public Object getData() {
        return this.get(KEY_DATA);
    }

    public void setData(Object data) {
        this.put(KEY_DATA, data);
    }


    private void init(int status, String message) {
        this.put(KEY_STATUS, status);
        this.put(KEY_MESSAGE, message);
    }

    private void init(Object data, Long count) {
        init(HttpServletResponse.SC_OK, "请求成功");
        this.put(KEY_DATA, data);
    }

}
