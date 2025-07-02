package service;

import model.Exemplaire;
import repository.ExemplaireRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExemplaireService {
    @Autowired
    private ExemplaireRepository exemplaireRepository;

    public List<Exemplaire> getAllExemplaires() {
        return exemplaireRepository.findAll();
    }

    public void saveExemplaire(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

    public Exemplaire getExemplaireById(Long id) {
        return exemplaireRepository.findById(id).orElse(null);
    }

    public void deleteExemplaire(Long id) {
        exemplaireRepository.deleteById(id);
    }

    public List<Exemplaire> getExemplairesByLivreId(Long livreId) {
        return exemplaireRepository.findByLivreId(livreId);
    }

    // public Optional<Exemplaire> findByCodeBarre(String codeBarre) {
    //     return exemplaireRepository.findByCodeBarre(codeBarre);
    // }

    public long countExemplairesDisponibles(Long livreId) {
        return exemplaireRepository.countExemplairesDisponiblesByLivreId(livreId);
    }
}