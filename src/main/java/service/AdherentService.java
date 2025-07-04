package service;

import model.Adherent;
import model.Emprunt;
import repository.AdherentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdherentService {
    private static final DateTimeFormatter DATE_FORMATTER = null;
    @Autowired
    private AdherentRepository adherentRepository;

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public void saveAdherent(Adherent adherent) {
        adherentRepository.save(adherent);
    }

    public Adherent getAdherentById(Long id) {
        return adherentRepository.findById(id).orElse(null);
    }

     public Adherent findByUsername(String username) {
        return adherentRepository.findByUsername(username).orElse(null);
    }

    public void deleteAdherent(Long id) {
        adherentRepository.deleteById(id);
    }

    public List<Adherent> searchAdherents(String searchTerm) {
        return adherentRepository.findByNomContainingOrPrenomContainingIgnoreCase(searchTerm, searchTerm);
    }

    // public Optional<Adherent> findByUsername(String email) {
    //     return adherentRepository.findByEmail(email);
    // }

    public List<Adherent> getAdherentsActifs() {
        return adherentRepository.findByActifTrue();
    }



       // Méthodes adaptées pour utiliser Emprunt


    public int countPretsEnCours(Long adherentId) {
        return adherentRepository.countEmpruntsEnCours(adherentId);
    }

    public int countReservationsEnAttente(Long adherentId) {
        // Implémentez cette méthode si vous avez une entité Reservation
        return adherentRepository.countReservationEnAttente(adherentId); // Temporaire
    }

    public String getProchainRetour(Long adherentId) {
        String date = adherentRepository.findProchainRetourDate(adherentId);
        if (date != null) {
            return date;
        }
        return "Aucun emprunt en cours";
    }

    public int countRetards(Long adherentId) {
        return adherentRepository.countEmpruntsEnRetard(adherentId);
    }

    public List<Emprunt> getPretsEnCours(Long adherentId) {
        List<Emprunt> emprunts = adherentRepository.findEmpruntsEnCours(adherentId);
        LocalDate aujourdhui = LocalDate.now();
        
        emprunts.forEach(emprunt -> {
            LocalDate dateRetour = emprunt.getDateRetourPrevue().toInstant()
                                         .atZone(ZoneId.systemDefault())
                                         .toLocalDate();
            
            // Mettre à jour le statut si nécessaire
            // if (dateRetour.isBefore(aujourdhui)) {
            //     emprunt.setStatut(Emprunt.StatutEmprunt.EN_RETARD);
            // }
            
            // Calculer si prolongation possible (exemple: pas encore prolongé)
            // Vous devrez ajouter un champ isProlonge dans Emprunt si nécessaire
        });
        
        return emprunts;
    }

    public void prolongerEmprunt(Long empruntId) {
        // Implémentez cette méthode en fonction de votre logique métier
        // Vous aurez besoin d'un EmpruntRepository
    }
}