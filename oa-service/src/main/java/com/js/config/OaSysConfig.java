package com.js.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 姜爽
 * @Description: OA系统常用配置信息
 * @Date: 2020/5/19 7:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class OaSysConfig {

    /**邮件发送者**/
    private String username;
}
