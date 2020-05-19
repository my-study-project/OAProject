package com.js.config.cross;

/**
 * @Author: 姜爽
 * @Description: //TODO
 * @Date: 2020/5/19 13:04
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * @author jiangshuang
 * Create on 2017年7月6日下午8:05:19
 * All right reserved
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        //允许任何域名
        corsConfiguration.addAllowedHeader("*");
        //允许任何头
        corsConfiguration.addAllowedMethod("*");
        //允许任何方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        //注册
        return new CorsFilter(source);
    }
}
