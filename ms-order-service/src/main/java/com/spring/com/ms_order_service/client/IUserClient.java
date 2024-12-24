package com.spring.com.ms_order_service.client;

import com.spring.com.ms_order_service.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user-service", url = "http://localhost:6002/api/users/")
public interface IUserClient {

    @GetMapping("/{id}")
    User getUserById(@PathVariable Long id);

}
