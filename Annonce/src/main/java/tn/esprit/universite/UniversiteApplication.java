package tn.esprit.universite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class UniversiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversiteApplication.class, args);
    }

}
