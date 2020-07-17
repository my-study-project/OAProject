package com.js;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: BaseTest
 * @Date: 2020/7/9 16:01
 * @Author: jiangshuang
 * @Description: 编写测试类公共父类
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BaseTest extends TestCase {

}
