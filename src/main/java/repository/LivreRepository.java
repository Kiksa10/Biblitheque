package repository;

import model.Livre;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}