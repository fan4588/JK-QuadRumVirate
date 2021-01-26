package com.nephew.jk.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLog implements Serializable {
    private Integer id;
    private String username;
    private String operation;
    private String module;
    private String type;
    private String method;
    private String params;
    private String result;
    private String ip;
    private Date createTime;
}