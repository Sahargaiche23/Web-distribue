package com.esprit.microservice.reservation.Repositories;

import com.esprit.microservice.reservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation,Long> {
    List<Reservation> findByLieuDepartContainingIgnoreCase(String lieuDepart);

    List<Reservation> findByLieuArriveeContainingIgnoreCase(String lieuArrivee);
}
