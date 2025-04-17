package com.example.eurkaserveur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurkaServeurApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurkaServeurApplication.class, args);
    }

}
