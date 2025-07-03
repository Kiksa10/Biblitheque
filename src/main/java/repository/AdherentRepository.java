package repository;

import model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
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
}