package tn.esprit.universite.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String typeAnnonce;
    private String auteurAnnonce;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime datePublication;

    private String image;
    private Long userId;

    // 🚆 Champs supplémentaires pour les annonces de train :
    private String lieuDepart;
    private String lieuArrivee;

    private String heureDepart;
    private String heureArrivee;

    private double prixTicket;
    private int nombrePlaces;

    // Getters et Setters

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public long getIdAnnonce() { return idAnnonce; }
    public void setIdAnnonce(long idAnnonce) { this.idAnnonce = idAnnonce; }

    public String getTitreAnnonce() { return titreAnnonce; }
    public void setTitreAnnonce(String titreAnnonce) { this.titreAnnonce = titreAnnonce; }

    public String getContenuAnnonce() { return contenuAnnonce; }
    public void setContenuAnnonce(String contenuAnnonce) { this.contenuAnnonce = contenuAnnonce; }

    public String getTypeAnnonce() { return typeAnnonce; }
    public void setTypeAnnonce(String typeAnnonce) { this.typeAnnonce = typeAnnonce; }

    public String getAuteurAnnonce() { return auteurAnnonce; }
    public void setAuteurAnnonce(String auteurAnnonce) { this.auteurAnnonce = auteurAnnonce; }

    public LocalDateTime getDatePublication() { return datePublication; }
    public void setDatePublication(LocalDateTime datePublication) { this.datePublication = datePublication; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getLieuDepart() { return lieuDepart; }
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }

    public String getLieuArrivee() { return lieuArrivee; }
    public void setLieuArrivee(String lieuArrivee) { this.lieuArrivee = lieuArrivee; }

    public String getHeureDepart() { return heureDepart; }
    public void setHeureDepart(String heureDepart) { this.heureDepart = heureDepart; }

    public String getHeureArrivee() { return heureArrivee; }
    public void setHeureArrivee(String heureArrivee) { this.heureArrivee = heureArrivee; }

    public double getPrixTicket() { return prixTicket; }
    public void setPrixTicket(double prixTicket) { this.prixTicket = prixTicket; }

    public int getNombrePlaces() { return nombrePlaces; }
    public void setNombrePlaces(int nombrePlaces) { this.nombrePlaces = nombrePlaces; }
}
