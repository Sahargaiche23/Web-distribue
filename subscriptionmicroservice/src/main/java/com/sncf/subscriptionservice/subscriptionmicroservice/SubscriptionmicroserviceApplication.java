package com.sncf.subscriptionservice.subscriptionmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubscriptionmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionmicroserviceApplication.class, args);
	}
}