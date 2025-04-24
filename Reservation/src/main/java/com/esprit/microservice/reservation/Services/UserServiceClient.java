package com.esprit.microservice.reservation.Services;

import com.esprit.microservice.reservation.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8085") // Change the port to your User Service port
public interface UserServiceClient {

    @GetMapping("/api/v1/users/{userId}")
    User getUserById(@PathVariable("userId") Long userId);
}
