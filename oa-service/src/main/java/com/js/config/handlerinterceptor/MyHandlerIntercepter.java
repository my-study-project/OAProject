package com.js.config.handlerinterceptor;


import com.js.common.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 姜爽
 * @Date 2019/12/3
 * @Description  拦截器设置 实现token拦截访问
 */
@Component
@Configuration
@Slf4j
public class MyHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("执行preHandle方法-->01");
        boolean status = false;
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Token");
        if (null != token) {
            boolean result = TokenUtil.verify(token);
            //验证token有效
            if (result) {
                status = true;
            }
        }
        return status;
    }
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("执行postHandle方法-->02");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        log.info("执行afterCompletion方法-->03");
    }
}
