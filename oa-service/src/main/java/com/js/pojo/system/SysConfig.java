package com.js.pojo.system;

import lombok.Data;
import lombok.ToString;

/**
 * @Author jiangshuang
 * @Description 系统常用配置文件
 **/
@ToString
@Data
public class SysConfig {
    private String uuid;

    private String name;

    private String value;

    private String confDesc;
}