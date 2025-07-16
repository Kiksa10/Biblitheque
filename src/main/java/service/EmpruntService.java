package service;

import model.Emprunt;
import repository.EmpruntRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;

    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    public void saveEmprunt(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }

    public void updateEmprunt(Long idAdherent , Long idLivre, Date dateRetourEffectif) {
        empruntRepository.updateEmprunt(idAdherent , idLivre , dateRetourEffectif);
    }

    public Emprunt getEmpruntById(Long id) {
        return empruntRepository.findById(id).orElse(null);
    }

    public List<Emprunt> getEmpruntsByAdherent(Long adherentId) {
        return empruntRepository.findByAdherentId(adherentId);
    }

    public List<Emprunt> getEmpruntsEnCours(Long adherentId) {
        return empruntRepository.findByAdherentId(adherentId);
    }

    // public List<Emprunt> getEmpruntsEnRetard() {
    //     return empruntRepository.findEmpruntsEnRetard();
    // }

    // public void retournerEmprunt(Long empruntId) {
    //     Emprunt emprunt = getEmpruntById(empruntId);
    //     if (emprunt != null) {
    //         emprunt.setStatut(Emprunt.StatutEmprunt.RETOURNE);
    //         emprunt.setDateRetourEffectif(new java.util.Date());
    //         saveEmprunt(emprunt);
    //     }
    // }
}