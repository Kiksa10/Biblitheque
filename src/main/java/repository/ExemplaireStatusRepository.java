package repository;

import model.ExemplaireStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireStatusRepository extends JpaRepository<ExemplaireStatus, Long> {
    // Trouver le dernier statut d'un exemplaire
    @Query("SELECT es FROM ExemplaireStatus es WHERE es.exemplaire.id = :exemplaireId ORDER BY es.dateAssociation DESC LIMIT 1")
    Optional<ExemplaireStatus> findLatestStatusByExemplaireId(@Param("exemplaireId") Long exemplaireId);
    
    // Historique des statuts d'un exemplaire
    List<ExemplaireStatus> findByExemplaireIdOrderByDateAssociationDesc(Long exemplaireId);
}