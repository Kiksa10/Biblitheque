package repository;

import model.Admin;
import model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    // Méthodes de base
    Optional<Admin> findByUsername(String username);
    
    List<Admin> findByNomContainingOrPrenomContainingIgnoreCase(String nom, String prenom);
    
    List<Admin> findByActifTrue();
    
    // Méthodes pour la gestion des emprunts
    @Query("SELECT e FROM Emprunt e WHERE e.dateRetourEffectif IS NULL")
    List<Emprunt> findAllCurrentEmprunts();
    
    @Query("SELECT e FROM Emprunt e WHERE e.dateRetourEffectif IS NULL AND e.dateRetourPrevue < CURRENT_DATE")
    List<Emprunt> findAllOverdueEmprunts();
    
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.dateRetourEffectif IS NULL")
    int countAllCurrentEmprunts();
    
    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.dateRetourEffectif IS NULL AND e.dateRetourPrevue < CURRENT_DATE")
    int countAllOverdueEmprunts();
    
    // Recherche avancée d'admins
    // @Query("SELECT a FROM Admin a WHERE LOWER(a.nom) LIKE LOWER(concat('%', :searchTerm, '%')) OR " +
    //        "LOWER(a.prenom) LIKE LOWER(concat('%', :searchTerm, '%'))")
    // List<Admin> searchAdmins(@Param("searchTerm") String searchTerm);
}