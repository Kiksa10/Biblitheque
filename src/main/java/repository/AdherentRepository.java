package repository;

import model.Adherent;
import model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    // Recherche par nom ou prénom
    List<Adherent> findByNomContainingOrPrenomContainingIgnoreCase(String nom, String prenom);
    
    // Trouver par email
    Optional<Adherent> findByEmail(String email);
    
    // Trouver les adhérents actifs
    List<Adherent> findByActifTrue();
    
    // Trouver les adhérents avec pénalité
    List<Adherent> findByPenaliteGreaterThan(Double montant);

    Optional<Adherent> findByUsername(String username);

    // Nouvelles méthodes pour les emprunts
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL")
    int countEmpruntsEnCours(Long adherentId);
    
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.adherent.id = :adherentId AND r.statut = 'EN_ATTENTE'")
    int countReservationEnAttente(Long adherentId);
    
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL AND e.dateRetourPrevue < CURRENT_DATE")
    int countEmpruntsEnRetard(Long adherentId);
    
    @Query("SELECT MIN(e.dateRetourPrevue) FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL")
    String findProchainRetourDate(Long adherentId);
    
    @Query("SELECT e FROM Emprunt e JOIN FETCH e.exemplaire WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL ORDER BY e.dateRetourPrevue ASC")
    List<Emprunt> findEmpruntsEnCours(@Param("adherentId") Long adherentId);

    // Méthodes pour gérer le nombre de prêts
    @Modifying
    @Transactional
    @Query("UPDATE Adherent a SET a.pretsEnCours = a.pretsEnCours + 1 WHERE a.id = :id")
    void incrementerNbrPret(@Param("id") Long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE Adherent a SET a.pretsEnCours = a.pretsEnCours - 1 WHERE a.id = :id AND a.pretsEnCours > 0")
    void decrementerNbrPret(@Param("id") Long id);

    // Méthodes pour gérer les pénalités
    @Modifying
    @Transactional
    @Query("UPDATE Adherent a SET a.penalite = a.penalite + :montant WHERE a.id = :id")
    void ajouterPenalite(@Param("id") Long id, @Param("montant") Double montant);
    
    @Modifying
    @Transactional
    @Query("UPDATE Adherent a SET a.penalite = 0 WHERE a.id = :id")
    void reinitialiserPenalite(@Param("id") Long id);
}