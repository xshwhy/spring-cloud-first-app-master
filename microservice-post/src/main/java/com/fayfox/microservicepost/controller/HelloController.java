package com.fayfox.microservicepost.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore //在Swagger中隐藏这个Controller
public class HelloController {
    @Value("${spring.application.name}")
    private String name;

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping("hello")
    public String index() {
        return "Hello, this is " + name + ", profile is " + profile;
    }
}
