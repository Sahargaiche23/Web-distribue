package com.sncft.train.Commands.controllers;

import com.sncft.train.Query.entities.User;
import com.sncft.train.Query.repos.UserRepository;
import com.sncft.train.commonApi.commands.CreateUserRequest;
import com.sncft.train.config.KeycloakAdminProperties;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final Keycloak keycloak;
    private final String realm = "oauth-train-realm"; // Keycloak realm name

    @Autowired
    private UserRepository userRepository;

    public UserController(KeycloakAdminProperties keycloakProps) {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:9072") // Keycloak server URL
                .realm("master") // Admin realm
                .clientId("admin-cli")
                .username("admin") // Keycloak admin username
                .password("admin") // Keycloak admin password
                .build();
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserRequest request) {
        // Create user in Keycloak
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.username);
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setEmail(request.email);
        user.setEnabled(true);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(request.password);

        user.setCredentials(Collections.singletonList(passwordCred));

        Response response = keycloak.realm(realm).users().create(user);

        // Save user in the database
        User dbUser = new User();
        dbUser.setUsername(request.username);
        dbUser.setFirstName(request.firstName);
        dbUser.setLastName(request.lastName);
        dbUser.setEmail(request.email);
        dbUser.setPassword(request.password); // You may want to encrypt the password before storing it

        userRepository.save(dbUser);

        return "Status: " + response.getStatus();
    }
}
