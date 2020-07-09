package com.js.mistake;

import com.js.BaseTest;
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
    }
}
