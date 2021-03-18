package com.fayfox.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("config")
public class ConfigClientController {
    @Value("${profile}")
    private String profile;

    @Value("${test.username}")
    private String username;

    @RequestMapping("profile")
    public String profile()
    {
        return profile;
    }

    @RequestMapping("username")
    public String username()
    {
        return username;
    }
}
