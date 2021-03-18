package com.fayfox.microservicepost.client;

import com.fayfox.dto.RespDTO;
import com.fayfox.microservicepost.client.hystrix.UserServiceClientFallback;
import com.fayfox.microservicepost.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-user", fallback = UserServiceClientFallback.class)
@Primary//不加这个idea会报there is more than one bean，但并不影响程序运行
public interface UserServiceClient {
    @GetMapping(value = "/user/{id}")
    RespDTO<User> getUser(@PathVariable("id") int id);
}
