package service;

import model.Abonnement;
import repository.AbonnementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AbonnementService {
    
    @Autowired
    private AbonnementRepository abonnementRepository;

    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }
    
    public Abonnement getAbonnementById(String id) {
        return abonnementRepository.findByAdherent(id).stream()
                .findFirst()
                .orElse(null);
    }
    
    public Abonnement saveOrUpdateAbonnement(Abonnement abonnement) {
        // Vérifier la validité de l'abonnement avant sauvegarde
        abonnement.setValide(abonnement.getDateFin().after(new Date()));
        return abonnementRepository.save(abonnement);
    }
    
    public void deleteAbonnement(Long id) {
        abonnementRepository.deleteById(id);
    }
    
    public List<Abonnement> getAbonnementsByAdherent(String adherent) {
        return abonnementRepository.findByAdherent(adherent);
    }
    
    public List<Abonnement> getValidAbonnements() {
        return abonnementRepository.findByValide(true);
    }
    
    public List<Abonnement> getExpiredAbonnements() {
        return abonnementRepository.findByDateFinBefore(new Date());
    }
}