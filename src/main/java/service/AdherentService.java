package service;

import model.Adherent;
import repository.AdherentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AdherentService {
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

    public void deleteAdherent(Long id) {
        adherentRepository.deleteById(id);
    }

    public List<Adherent> searchAdherents(String searchTerm) {
        return adherentRepository.findByNomContainingOrPrenomContainingIgnoreCase(searchTerm, searchTerm);
    }

    // public Optional<Adherent> findByEmail(String email) {
    //     return adherentRepository.findByEmail(email);
    // }

    public List<Adherent> getAdherentsActifs() {
        return adherentRepository.findByActifTrue();
    }
}