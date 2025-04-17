# 💌 Projet Spring Boot - Gestion des Réclamations

Projet backend en Spring Boot pour gérer des réclamations de transport.  
Chaque réclamation contient le lieu de départ, lieu d’arrivée, horaires, une description, et un statut de traitement.

---

## 🛠️ Stack technique

- 🧩 Spring Boot
- 🔗 Spring Data JPA
- 💽 MySQL
- 📬 Postman pour les tests API
- 🌿 Lombok

---

## 🧱 Entité `Reclamation`

```java
@Entity
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamation;
    private String lieuDepart;
    private String lieuArrivee;
    private Date heureDepart;
    private Date heureArrivee;
    private String description;
    private String statut;
}
