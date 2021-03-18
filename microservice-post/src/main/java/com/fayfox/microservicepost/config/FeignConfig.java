package com.fayfox.microservicepost.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer feignRetryer() {
        //重试间隔100毫秒，最大重试时间1秒，最多重试5次
        return new Retryer.Default(100, SECONDS.toMillis(1), 5);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
