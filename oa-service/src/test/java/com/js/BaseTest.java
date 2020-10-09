package com.js;

import junit.framework.TestCase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @program: BaseTest
 * @Date: 2020/7/9 16:01
 * @Author: jiangshuang
 * @Description: 编写测试类公共父类
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class BaseTest extends TestCase {

}
