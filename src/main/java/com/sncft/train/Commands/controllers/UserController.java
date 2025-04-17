package com.sncft.train.Commands.controllers;

import com.sncft.train.Query.entities.User;
import com.sncft.train.Query.repos.UserRepository;
import com.sncft.train.commonApi.commands.CreateUserRequest;
import com.sncft.train.config.KeycloakAdminProperties;
import com.sncft.train.configgmail.MailService;
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
    @Autowired
    private MailService mailService;

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

        // Création de l'utilisateur dans Keycloak
        Response response = keycloak.realm(realm).users().create(user);

        if (response.getStatus() == 201) {  // L'utilisateur a été créé avec succès
            // Récupérer l'ID de l'utilisateur créé dans Keycloak
            String keycloakUserId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            // Save user in the database with the same ID as Keycloak
            User dbUser = new User();
            dbUser.setUsername(request.username);
            dbUser.setFirstName(request.firstName);
            dbUser.setLastName(request.lastName);
            dbUser.setEmail(request.email);
            dbUser.setPassword(request.password);
            dbUser.setPhoto(request.photo);
            dbUser.setKeycloakUserId(keycloakUserId);  // Stocker l'ID de Keycloak

            userRepository.save(dbUser);

            // Envoi de mail
            String subject = "Bienvenue chez SNCFT 🚆";
            String body = "Bonjour " + request.firstName + ", votre compte a bien été créé.";
            mailService.sendTextEmail(request.email, subject, body);

            return "User created successfully with Keycloak ID: " + keycloakUserId;
        } else {
            return "Failed to create user in Keycloak. Status: " + response.getStatus();
        }
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
            // Vérifier si l'utilisateur existe dans Keycloak
            UserRepresentation userRepresentation = null;
            try {
                userRepresentation = keycloak.realm(realm).users().get(userId).toRepresentation();
            } catch (Exception e) {
                return "Update failed: User not found in Keycloak.";
            }

            if (userRepresentation == null) {
                return "Update failed: User not found in Keycloak.";
            }

            // Mise à jour des informations dans Keycloak
            userRepresentation.setFirstName(request.firstName);
            userRepresentation.setLastName(request.lastName);
            userRepresentation.setEmail(request.email);

            // Mettre à jour l'utilisateur dans Keycloak
            keycloak.realm(realm).users().get(userId).update(userRepresentation);

            // Mise à jour dans la base de données
            User dbUser = userRepository.findById(Long.valueOf(userId)).orElse(null);
            if (dbUser != null) {
                dbUser.setFirstName(request.firstName);
                dbUser.setLastName(request.lastName);
                dbUser.setEmail(request.email);
                dbUser.setPassword(request.password);  // Gérer le mot de passe de manière sécurisée si nécessaire
                dbUser.setPhoto(request.photo);

                userRepository.save(dbUser);
                return "User updated successfully.";
            } else {
                return "Update failed: User not found in the database.";
            }
        } catch (Exception e) {
            return "Update failed: " + e.getMessage();
        }
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        try {
            // Vérification si l'utilisateur existe dans Keycloak
            boolean userExistsInKeycloak = keycloak.realm(realm).users().get(userId).toRepresentation() != null;
            if (userExistsInKeycloak) {
                // Suppression de l'utilisateur dans Keycloak
                keycloak.realm(realm).users().get(userId).remove();

                // Suppression de l'utilisateur dans la base de données
                userRepository.deleteById(Long.valueOf(userId));

                return "User deleted successfully.";
            } else {
                return "User not found in Keycloak.";
            }
        } catch (Exception e) {
            return "Deletion failed: " + e.getMessage();
        }
    }

    @GetMapping("/test")
    public String test() {
        return "User service is working ApiGetway!";
    }

}
