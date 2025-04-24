package com.example.reclamation.Controllers;



import com.example.reclamation.Services.IReclamationService;
import com.example.reclamation.entities.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/reclamation")
public class ReclamationController {

    private final IReclamationService reclamationService;

    @Autowired
    public ReclamationController(IReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    // Ajouter une réclamation
    @PostMapping("/addReclamation")
    public ResponseEntity<?> addReclamation(@RequestBody Reclamation reclamation ) {
        try {
            if (reclamation == null) {
                return ResponseEntity.badRequest().body("Reclamation cannot be null");
            }

            Reclamation result = reclamationService.addReclamation(reclamation);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding reclamation: " + e.getMessage());
        }
    }

    // Récupérer une réclamation par son ID
    @GetMapping("/reclamation/{id}")
    public ResponseEntity<?> getReclamation(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("ID cannot be null");
            }

            Reclamation result = reclamationService.getReclamationById(id);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving reclamation: " + e.getMessage());
        }
    }

    // Récupérer toutes les réclamations
    @GetMapping("/reclamations")
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        try {
            List<Reclamation> result = reclamationService.getAllReclamations();
            return ResponseEntity.ok(result != null ? result : List.of());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }

    // Mettre à jour une réclamation
    @PatchMapping("/reclamation")
    public ResponseEntity<?> updateReclamation(@RequestBody Reclamation reclamation) {
        try {
            if (reclamation == null) {
                return ResponseEntity.badRequest().body("Reclamation cannot be null");
            }

            Reclamation result = reclamationService.updateReclamation(reclamation.getIdReclamation(), reclamation);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating reclamation: " + e.getMessage());
        }
    }

    // Supprimer une réclamation par son ID
    @DeleteMapping("/reclamation/{id}")
    public ResponseEntity<String> deleteReclamation(@PathVariable Long id) {
        try {
            reclamationService.deleteReclamation(id);
            return ResponseEntity.ok("Reclamation deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting reclamation: " + e.getMessage());
        }
    }

    // Rechercher les réclamations par statut
    @GetMapping("/searchByStatut/{statut}")
    public ResponseEntity<List<Reclamation>> getReclamationsByStatut(@PathVariable String statut) {
        try {
            if (statut == null || statut.isEmpty()) {
                return ResponseEntity.badRequest().body(List.of());
            }

            List<Reclamation> result = reclamationService.getReclamationsByStatut(statut);
            return ResponseEntity.ok(result != null ? result : List.of());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(List.of());
        }
    }
}

