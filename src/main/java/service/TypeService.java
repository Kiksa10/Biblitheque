package service;

import jakarta.transaction.Transactional;
import model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TypeRepository;

import java.util.List;

@Service
@Transactional
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    public Type getTypeById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type non trouvé avec l'id : " + id));
    }
}

