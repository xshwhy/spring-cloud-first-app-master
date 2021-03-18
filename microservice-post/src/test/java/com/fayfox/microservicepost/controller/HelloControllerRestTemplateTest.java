package com.fayfox.microservicepost.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 利用RestTemplate，走http请求进行测试
 */
@RunWith(SpringRunner.class)
// 项目本身不能在运行状态，如果跑着会提示“Failed to load ApplicationContext”
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerRestTemplateTest {
    @LocalServerPort
    private int port;

    //不加上面的webEnvironment，这个TestRestTemplate注入不了
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void index() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/hello", String.class))
                .contains("Hello");
    }
}