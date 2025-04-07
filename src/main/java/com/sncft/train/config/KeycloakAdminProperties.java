package com.sncft.train.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;

@Primary
@Configuration
@Data
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakAdminProperties {
    private String clientId;
    private String clientSecret;
    private String authServerUrl;

    // getters et setters
}
