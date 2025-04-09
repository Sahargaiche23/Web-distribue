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
@CrossOrigin(origins = "http://localhost:4200")
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
        dbUser.setPassword(request.password);

        userRepository.save(dbUser);

        return "Status: " + response.getStatus();
    }

    @PostMapping("/login")
    public String login(@RequestBody CreateUserRequest request) {
        try {
            Keycloak keycloakLogin = KeycloakBuilder.builder()
                    .serverUrl("http://localhost:9072")
                    .realm("oauth-train-realm")
                    .clientId("sahar")
                    .clientSecret("6PrXXVPSZZtDdUQE7REd1S3JZ4PMhZsM")
                    .username(request.username)
                    .password(request.password)
                    .grantType("password")
                    .build();

            String token = keycloakLogin.tokenManager().getAccessTokenString();
            return "Access Token: " + token;

        } catch (Exception e) {
            return "Login failed: " + e.getMessage();
        }
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable String userId, @RequestBody CreateUserRequest request) {
        try {
            UserRepresentation user = keycloak.realm(realm).users().get(userId).toRepresentation();

            user.setFirstName(request.firstName);
            user.setLastName(request.lastName);
            user.setEmail(request.email);

            keycloak.realm(realm).users().get(userId).update(user);

            User dbUser = userRepository.findById(Long.valueOf(userId)).orElse(null);
            if (dbUser != null) {
                dbUser.setFirstName(request.firstName);
                dbUser.setLastName(request.lastName);
                dbUser.setEmail(request.email);
                userRepository.save(dbUser);
            }

            return "User updated successfully.";
        } catch (Exception e) {
            return "Update failed: " + e.getMessage();
        }
    }
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        try {
            // Delete from Keycloak
            keycloak.realm(realm).users().get(userId).remove();

            // Delete from DB
            userRepository.deleteById(Long.valueOf(userId));

            return "User deleted successfully.";
        } catch (Exception e) {
            return "Deletion failed: " + e.getMessage();
        }
    }

}
