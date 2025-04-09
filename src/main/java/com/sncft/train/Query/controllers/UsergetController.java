package com.sncft.train.Query.controllers;

import com.sncft.train.Query.entities.User;
import com.sncft.train.Query.repos.UserRepository;
import com.sncft.train.config.KeycloakAdminProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UsergetController {


    private final Keycloak keycloak;
    private final String realm = "oauth-train-realm"; // Keycloak realm name

    @Autowired
    private UserRepository userRepository;

    public  UsergetController(KeycloakAdminProperties keycloakProps) {
        this.keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:9072") // Keycloak server URL
                .realm("master") // Admin realm
                .clientId("admin-cli")
                .username("admin") // Keycloak admin username
                .password("admin") // Keycloak admin password
                .build();
    }

    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll(); // Retourner tous les utilisateurs depuis la base de données
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve users: " + e.getMessage());
        }
    }
    // Nouvelle méthode pour obtenir un utilisateur par son nom d'utilisateur
    @GetMapping("/{email}")
    public User getUserByUsername(@PathVariable String email) {
        try {
            // Recherche l'utilisateur dans la base de données par son nom d'utilisateur
            User dbUser = userRepository.findByUsername(email);
            if (dbUser == null) {
                throw new RuntimeException("User not found with username: " + email);
            }
            return dbUser;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user by username: " + e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) {
        try {
            User dbUser = userRepository.findById(Long.valueOf(userId)).orElse(null);
            if (dbUser == null) {
                throw new RuntimeException("User not found with id: " + userId);
            }
            return dbUser;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve user: " + e.getMessage());
        }
    }
}
