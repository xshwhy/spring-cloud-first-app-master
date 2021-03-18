package com.fayfox.microservicepost.client.hystrix;

import com.fayfox.dto.RespDTO;
import com.fayfox.microservicepost.client.UserServiceClient;
import com.fayfox.microservicepost.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallback implements UserServiceClient {
    @Override
    public RespDTO<User> getUser(int id) {
        System.out.println("请求用户[" + id + "]超时");
        return null;
    }
}
