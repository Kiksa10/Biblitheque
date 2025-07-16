package repository;

import model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

   @Modifying
    @Transactional
    @Query("UPDATE Emprunt e SET e.dateRetourEffectif = :dateRetour, e.statut = 'retourne' " +
        "WHERE e.adherent.id = :adherentId AND e.exemplaire.id = :exemplaireId AND e.dateRetourEffectif IS NULL")
    void updateEmprunt(@Param("adherentId") Long adherentId, 
                    @Param("exemplaireId") Long exemplaireId, 
                    @Param("dateRetour") Date dateRetour);
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
    
    // @Query("SELECT COUNT(e) > 0 FROM Emprunt e " +
    //        "WHERE e.adherent.id = :adherentId " +
    //        "AND e.statut = 'EN_RETARD' " +
    //        "AND e.dateRetour IS NULL")
    // boolean existsByAdherentIdAndStatutEnRetard(@Param("adherentId") Long adherentId);
    
}