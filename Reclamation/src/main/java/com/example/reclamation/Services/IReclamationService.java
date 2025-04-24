package com.example.reclamation.Services;

import com.example.reclamation.entities.Reclamation;

import java.util.List;

public interface IReclamationService {
    Reclamation addReclamation(Reclamation reclamation);
    Reclamation updateReclamation(Long id, Reclamation reclamation);
    void deleteReclamation(Long id);
    Reclamation getReclamationById(Long id);
    List<Reclamation> getAllReclamations();
    List<Reclamation> getReclamationsByStatut(String statut);
}
