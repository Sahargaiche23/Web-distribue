package com.esprit.microservice.reservation.Controllers;

import com.esprit.microservice.reservation.Services.IReservationService;
import com.esprit.microservice.reservation.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/reservation")
public class ReservationControllers {
    @Autowired
    private IReservationService reservationService;

    // Ajouter une réservation
    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        try {
            if (reservation == null) {
                return ResponseEntity.badRequest().body("Reservation cannot be null");
            }

            Reservation result = reservationService.addReservation(reservation);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding reservation: " + e.getMessage());
        }
    }

    // Récupérer une réservation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getReservation(@PathVariable Long id) {
        try {
            if (id == null) {
                return ResponseEntity.badRequest().body("ID cannot be null");
            }

            Reservation result = reservationService.getReservation(id);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving reservation: " + e.getMessage());
        }
    }

    // Récupérer toutes les réservations
    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        try {
            List<Reservation> result = reservationService.getAllReservations();
            return ResponseEntity.ok(result != null ? result : new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    // Mettre à jour une réservation
    @PutMapping("/update")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        try {
            if (reservation == null) {
                return ResponseEntity.badRequest().body("Reservation cannot be null");
            }

            Reservation result = reservationService.updateReservation(reservation);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating reservation: " + e.getMessage());
        }
    }

    // Supprimer une réservation par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.ok("Reservation deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting reservation: " + e.getMessage());
        }
    }

    // Rechercher des réservations par lieu de départ
    @GetMapping("/search/departure/{lieuDepart}")
    public ResponseEntity<List<Reservation>> searchByLieuDepart(@PathVariable String lieuDepart) {
        try {
            if (lieuDepart == null || lieuDepart.isEmpty()) {
                return ResponseEntity.badRequest().body(new ArrayList<>());
            }

            List<Reservation> result = reservationService.searchByLieuDepart(lieuDepart);
            return ResponseEntity.ok(result != null ? result : new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    // Rechercher des réservations par lieu d'arrivée
    @GetMapping("/search/arrival/{lieuArrivee}")
    public ResponseEntity<List<Reservation>> searchByLieuArrivee(@PathVariable String lieuArrivee) {
        try {
            if (lieuArrivee == null || lieuArrivee.isEmpty()) {
                return ResponseEntity.badRequest().body(new ArrayList<>());
            }

            List<Reservation> result = reservationService.searchByLieuArrivee(lieuArrivee);
            return ResponseEntity.ok(result != null ? result : new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }
}
