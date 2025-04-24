package com.example.reclamation.entities;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import java.util.Date;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Long userId;




}
