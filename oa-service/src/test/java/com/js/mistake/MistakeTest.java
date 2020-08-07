package com.js.mistake;

import com.js.BaseTest;
import com.js.common.util.httpclient.HttpClientResult;
import com.js.common.util.httpclient.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: OAProject
 * @Date: 2020/7/9 16:08
 * @Author: jiangshuang
 * @Description:Mistake相关测试类
 */
@Slf4j
public class MistakeTest extends BaseTest {
    @Before
    public void beforeTest() {
        log.info("你的程序出错了");
    }

    @Test
    public void test1() {
        log.info("这是一个测试类");
        try {
            HttpClientResult httpClientResult =HttpClientUtils.doGet("http://mark-coupon-test.suixingpay.com/admin/couponSell/1");
            log.info(httpClientResult.getContent());
        }catch (Exception e) {
            log.info("test1");
        }
    }
    @Test
    public void test2(){
//        RabbitUtil.Publisher();
//        RabbitUtil.Consumer();
    }
}
