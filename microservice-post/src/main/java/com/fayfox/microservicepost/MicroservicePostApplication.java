package com.fayfox.microservicepost;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.fayfox.microservicepost.client")
@MapperScan("com.fayfox.microservicepost.mapper")
public class MicroservicePostApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicePostApplication.class, args);
    }

}
