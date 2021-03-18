package com.fayfox.microserviceuser.controller;

import com.fayfox.exception.CommonException;
import com.fayfox.microserviceuser.mapper.UserMapper;
import com.fayfox.dto.RespDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("{id}")
    public RespDTO item(@PathVariable(name = "id") int id) {
        if ((int)(Math.random() * 2) == 1) {
            //一定概率超时，测试feign重试机制
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                System.out.println("我超时了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return RespDTO.success(userMapper.findById(id));
    }
}
