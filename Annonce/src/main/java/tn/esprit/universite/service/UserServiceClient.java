package tn.esprit.universite.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.universite.entities.User;

@FeignClient(name = "user-service", url = "http://localhost:8085") // Change the port to your User Service port
public interface UserServiceClient {

    @GetMapping("/api/v1/users/{userId}")
    User getUserById(@PathVariable("userId") Long userId);
}
