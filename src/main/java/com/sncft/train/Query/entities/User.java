package com.sncft.train.Query.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant unique de l'utilisateur

    @Column(length = 255, nullable = false, unique = true) // Username unique et obligatoire
    private String username;

    @Column(length = 255, nullable = false) // Prénom de l'utilisateur, obligatoire
    private String firstName;

    @Column(length = 255, nullable = false) // Nom de famille de l'utilisateur, obligatoire
    private String lastName;

    @Column(length = 255, nullable = false, unique = true) // Email unique et obligatoire
    private String email;

    @Column(length = 255, nullable = false) // Mot de passe de l'utilisateur, obligatoire
    private String password;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String photo;
    @Column(length = 255, nullable = false) // Mot de passe de l'utilisateur, obligatoire

    private String keycloakUserId;






    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getKeycloakUserId() {
        return keycloakUserId;
    }

    public void setKeycloakUserId(String keycloakUserId) {
        this.keycloakUserId = keycloakUserId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }




    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +

                '}';
    }
}
