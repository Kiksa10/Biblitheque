package repository;

import model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    // Emprunts d'un adhérent
    List<Emprunt> findByAdherentId(Long adherentId);
    
    // Emprunts en cours
    // List<Emprunt> findByStatut(Emprunt.StatutEmprunt statut);
    
    // Emprunts en retard
    // @Query("SELECT e FROM Emprunt e WHERE e.statut = 'EN_RETARD' OR (e.statut = 'EN_COURS' AND e.dateRetourPrevue < CURRENT_DATE)")
    // List<Emprunt> findEmpruntsEnRetard();
    
    // Emprunts d'un exemplaire
    List<Emprunt> findByExemplaireId(Long exemplaireId);


    //     @Query("SELECT e FROM Emprunt e JOIN FETCH e.exemplaire WHERE e.adherent.id = :adherentId AND e.dateRetourEffectif IS NULL ORDER BY e.dateRetourPrevue ASC")
    // List<Emprunt> findEmpruntsEnCours(@Param("adherentId") Long adherentId);
    
    // // @Query("SELECT e FROM Emprunt e WHERE e.adherent.id = :adherentId AND e.statut = 'EN_RETARD'")
    // // List<Emprunt> findEmpruntsEnRetard(@Param("adherentId") Long adherentId);

    //  @Query("SELECT e FROM Emprunt e " +
    //        "JOIN FETCH e.exemplaire ex " +
    //        "JOIN FETCH ex.livre " +
    //        "WHERE e.adherent.id = :adherentId " +
    //        "AND e.dateRetourEffectif IS NULL " +
    //        "ORDER BY e.dateRetourPrevue ASC")
    // List<Emprunt> findEmpruntsEnCoursWithDetails(@Param("adherentId") Long adherentId);
    
}