package service;

import model.Reservation;
import repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getReservationsByAdherent(Long adherentId) {
        return reservationRepository.findByAdherentId(adherentId);
    }

    public List<Reservation> getReservationsByLivre(Long livreId) {
        return reservationRepository.findByLivreId(livreId);
    }

    public List<Reservation> getReservationsEnAttente() {
        // Pour les requêtes basées sur le statut
    List<Reservation> enAttente = reservationRepository.findByStatut(Reservation.StatutReservation.EN_ATTENTE);
        return enAttente;
    }

    public void annulerReservation(Long reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null) {
            reservation.setStatut(Reservation.StatutReservation.ANNULEE);
            saveReservation(reservation);
        }
    }

    public void expirerReservations() {
        List<Reservation> reservationsExpirees = reservationRepository.findReservationsExpirees();
        for (Reservation reservation : reservationsExpirees) {
            reservation.setStatut(Reservation.StatutReservation.EXPIREE);
            saveReservation(reservation);
        }
    }
}