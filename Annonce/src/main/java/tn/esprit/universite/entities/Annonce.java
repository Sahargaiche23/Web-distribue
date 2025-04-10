package tn.esprit.universite.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Annonce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnnonce;

    private String titreAnnonce;
    private String contenuAnnonce;
    private String typeAnnonce; // Par exemple : "Retard", "Grève", "Info"
    private String auteurAnnonce;
    private LocalDateTime datePublication;
    private String image; // Nom ou chemin de l’image associée à l’annonce

    // Getters and Setters
    public long getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(long idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public String getTitreAnnonce() {
        return titreAnnonce;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public String getContenuAnnonce() {
        return contenuAnnonce;
    }

    public void setContenuAnnonce(String contenuAnnonce) {
        this.contenuAnnonce = contenuAnnonce;
    }

    public String getTypeAnnonce() {
        return typeAnnonce;
    }

    public void setTypeAnnonce(String typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    public String getAuteurAnnonce() {
        return auteurAnnonce;
    }

    public void setAuteurAnnonce(String auteurAnnonce) {
        this.auteurAnnonce = auteurAnnonce;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
