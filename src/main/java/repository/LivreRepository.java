package repository;

import model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    // Recherche par titre (contient)
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    
    // Recherche par auteur
    List<Livre> findByAuteurContainingIgnoreCase(String auteur);
    
    // Recherche par genre
    List<Livre> findByGenre(String genre);
    
    // Recherche par ISBN
    Optional<Livre> findByIsbn(String isbn);

    @Query("SELECT l FROM Livre l WHERE l.id = :id")
    Optional<Livre> findByIdWithExemplaires(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Livre l SET l.nbrExemplaire = l.nbrExemplaire - 1 WHERE l.id = :id")
    void decrementerExemplaire(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Livre l SET l.nbrExemplaire = l.nbrExemplaire + 1 WHERE l.id = :id")
    void incrementerExemplaire(@Param("id") Long id);
}