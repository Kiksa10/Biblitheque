package repository;

import model.Status;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    // Trouver par état
    Optional<Status> findByEtat(Status.EtatExemplaire etat);
}