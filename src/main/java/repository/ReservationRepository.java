package repository;

import model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Réservations d'un adhérent
    List<Reservation> findByAdherentId(Long adherentId);
    
    // Réservations d'un livre
    List<Reservation> findByLivreId(Long livreId);
    
    // Réservations actives (en attente)
    List<Reservation> findByStatut(Reservation.StatutReservation statut);
    
    // Réservations expirées
    @Query("SELECT r FROM Reservation r WHERE r.dateExpiration < CURRENT_DATE AND r.statut = 'EN_ATTENTE'")
    List<Reservation> findReservationsExpirees();
}