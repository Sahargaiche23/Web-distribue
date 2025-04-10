package tn.esprit.universite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.universite.entities.Annonce;
import tn.esprit.universite.services.IAnnonceService;
import tn.esprit.universite.service.pdfService;




import java.util.ArrayList;
import java.util.List;


// this my controller
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/annonce")
public class AnnonceController {

    private final IAnnonceService annonceService;
    private final pdfService pdfService;

    @Autowired
    public AnnonceController(IAnnonceService annonceService, pdfService pdfService) {
        this.annonceService = annonceService;
        this.pdfService = pdfService;
    }

    // Ajouter une annonce
    @PostMapping("/addannonce")
    public ResponseEntity<byte[]> addAnnonce(@ModelAttribute Annonce annonce,
                                             @RequestParam(value = "file", required = false) MultipartFile imageFile) {
        try {
            if (annonce == null) {
                return ResponseEntity.badRequest().body("Annonce cannot be null".getBytes());
            }

            Annonce result = annonceService.addAnnonce(annonce, imageFile);

            // Generate PDF after adding the annonce
            StringBuilder content = new StringBuilder();
            content.append("Title: ").append(result.getTitreAnnonce()).append("\n")
                    .append("Content: ").append(result.getContenuAnnonce()).append("\n")
                    .append("Type: ").append(result.getTypeAnnonce()).append("\n")
                    .append("Author: ").append(result.getAuteurAnnonce()).append("\n")
                    .append("Publication Date: ").append(result.getDatePublication()).append("\n");


            byte[] pdfBytes = pdfService.generatePdf(content.toString());

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"annonce.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error adding annonce: " + e.getMessage()).getBytes());
        }
    }




    // Récupérer une annonce par son ID
    @GetMapping("/annonce/{id}")
    public ResponseEntity<?> getAnnonce(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("ID cannot be null");
            }

            Annonce result = annonceService.getAnnonce(id);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving annonce: " + e.getMessage());
        }
    }



    // Récupérer toutes les annonces
    @GetMapping("/annonces")
    public ResponseEntity<List<Annonce>> getAllAnnonces() {
        try {
            List<Annonce> result = annonceService.getAllAnnonces();
            return ResponseEntity.ok(result != null ? result : new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }



    // Mettre à jour une annonce
    @PatchMapping("/annonce")
    public ResponseEntity<?> updateAnnonce(@ModelAttribute Annonce annonce,
                                           @RequestParam(value = "file", required = false) MultipartFile imageFile) {
        try {
            if (annonce == null) {
                return ResponseEntity.badRequest().body("Annonce cannot be null");
            }

            Annonce result = annonceService.updateAnnonce(annonce, imageFile);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating annonce: " + e.getMessage());
        }
    }




    // Supprimer une annonce par son ID
    @DeleteMapping("/annonce/{id}")
    public ResponseEntity<String> deleteAnnonce(@PathVariable long id) {
        try {
            annonceService.deleteAnnonce(id);
            return ResponseEntity.ok("Annonce deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting annonce: " + e.getMessage());
        }
    }




    // Rechercher une annonce par son titre
    @GetMapping("/searchAnnonce/{titreAnnonce}")
    public ResponseEntity<List<Annonce>> rechercherParTitre(@PathVariable String titreAnnonce) {
        try {
            if (titreAnnonce == null || titreAnnonce.isEmpty()) {
                return ResponseEntity.badRequest().body(new ArrayList<>());
            }

            List<Annonce> result = annonceService.rechercherParTitre(titreAnnonce);
            return ResponseEntity.ok(result != null ? result : new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }





}
