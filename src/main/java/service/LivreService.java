package service;

import model.Livre;
import repository.LivreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LivreService {
    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
    
    public void enleverUnExemplaire(Long id) {
         livreRepository.decrementerExemplaire(id);
    }

    public void ajouterNbrExemplaire(Long id) {
         livreRepository.incrementerExemplaire(id);
    }

    public void saveLivre(Livre livre) {
        livreRepository.save(livre);
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public List<Livre> searchByTitre(String titre) {
        return livreRepository.findByTitreContainingIgnoreCase(titre);
    }

    public Livre getLivreWithExemplaires(Long id) {
    return livreRepository.findByIdWithExemplaires(id)
            .orElseThrow(() -> new EntityNotFoundException("Livre non trouvé"));
}

    // public List<Livre> searchByAuteur(String auteur) {
    //     return livreRepository.findByAuteurContainingIgnoreCase(auteur);
    // }

    // public Optional<Livre> findByIsbn(String isbn) {
    //     return livreRepository.findByIsbn(isbn);
    // }
}