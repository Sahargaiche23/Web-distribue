package com.esprit.microservice.reservation.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String lieuDepart;
    private String lieuArrivee;
    private Date heureDepart;
    private Date heureArrivee;
    private Long Nbrplaces ;
    private float prixticket;
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Date getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public Long getNbrplaces() {
        return Nbrplaces;
    }

    public void setNbrplaces(Long nbrplaces) {
        Nbrplaces = nbrplaces;
    }

    public float getPrixticket() {
        return prixticket;
    }

    public void setPrixticket(float prixticket) {
        this.prixticket = prixticket;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", lieuDepart='" + lieuDepart + '\'' +
                ", lieuArrivee='" + lieuArrivee + '\'' +
                ", heureDepart=" + heureDepart +
                ", heureArrivee=" + heureArrivee +
                ", Nbrplaces=" + Nbrplaces +
                ", prixticket=" + prixticket +
                '}';
    }
}
