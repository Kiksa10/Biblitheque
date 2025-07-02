package service;

import jakarta.transaction.Transactional;
import model.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategorieRepository;

import java.util.List;

@Service
@Transactional
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public List<Categorie> getCategoriesByIds(List<Long> ids) {
        return categorieRepository.findAllById(ids);
    }
}