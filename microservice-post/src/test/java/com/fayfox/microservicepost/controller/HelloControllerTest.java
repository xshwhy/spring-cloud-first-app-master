package com.fayfox.microservicepost.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 直接调用方法测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {
    @Autowired
    private HelloController helloController;

    @Test
    public void getHello() {
        final String greeting = helloController.index();
        Assertions.assertThat(greeting).contains("Hello");
    }
}