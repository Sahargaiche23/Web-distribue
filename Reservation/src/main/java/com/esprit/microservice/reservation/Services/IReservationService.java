package com.esprit.microservice.reservation.Services;

import com.esprit.microservice.reservation.entities.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation addReservation(Reservation reservation);
    Reservation getReservation(Long id);
    List<Reservation> getAllReservations();
    Reservation updateReservation(Reservation reservation);
    void deleteReservation(long id);
    List<Reservation> searchByLieuDepart(String lieuDepart);
    List<Reservation> searchByLieuArrivee(String lieuArrivee);
}
