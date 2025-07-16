package repository;

import model.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;



@Repository
public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    
    // Méthodes personnalisées
    List<Abonnement> findByAdherent(String adherent);
    
    List<Abonnement> findByValide(boolean valide);
    
    List<Abonnement> findByDateFinBefore(Date date);
}