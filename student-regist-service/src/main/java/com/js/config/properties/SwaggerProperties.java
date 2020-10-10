package com.js.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: OAProject
 * @Date: 2020/10/10 8:58
 * @Author: jiangshuang
 * @Description:
 */

@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {
    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    private Boolean enable = Boolean.TRUE;

    /**
     * 项目应用名
     */
    private String applicationName;

    /**
     * 项目版本信息
     */
    private String applicationVersion = "Version-1.0";

    /**
     * 项目描述信息
     */
    private String applicationDescription;

    /**
     * 接口调试地址
     */
    private String tryHost;

}
