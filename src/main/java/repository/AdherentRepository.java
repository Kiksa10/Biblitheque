package repository;

import model.Adherent;
import model.Emprunt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


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
    
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL AND e.dateRetourPrevue < CURRENT_DATE")
    int countEmpruntsEnRetard(Long adherentId);
    
    @Query("SELECT MIN(e.dateRetourPrevue) FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL")
    String findProchainRetourDate(Long adherentId);

    @Query("SELECT e FROM Emprunt e JOIN FETCH e.exemplaire WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL ORDER BY e.dateRetourPrevue ASC")
    List<Emprunt> findEmpruntsEnCours(@Param("adherentId") Long adherentId);
    
    // @Query("SELECT e FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.statut = 'EN_RETARD'")
    // List<Emprunt> findEmpruntsEnRetard(@Param("adherentId") Long adherentId);
}