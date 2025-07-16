package repository;

import model.Exemplaire;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    // Trouver tous les exemplaires d'un livre
    List<Exemplaire> findByLivreId(Long livreId);
    
    // Trouver par code barre
    Optional<Exemplaire> findByCodeBarre(String codeBarre);
    
    // Compter les exemplaires disponibles d'un livre
    @Query("SELECT COUNT(e) FROM Exemplaire e JOIN e.statusHistory s WHERE e.livre.id = :livreId AND s.status.etat = 'DISPONIBLE'")
    long countExemplairesDisponiblesByLivreId(@Param("livreId") Long livreId);

    @Query("SELECT e FROM Exemplaire e JOIN e.statusHistory s WHERE e.livre.id = :livreId AND s.status.etat = 'DISPONIBLE'")
    List<Exemplaire> getExemplaireDispo(@Param("livreId") Long livreId);
}