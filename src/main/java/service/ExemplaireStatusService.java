package service;

import model.ExemplaireStatus;
import repository.ExemplaireStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExemplaireStatusService {
    @Autowired
    private ExemplaireStatusRepository exemplaireStatusRepository;

    public ExemplaireStatus saveExemplaireStatus(ExemplaireStatus exemplaireStatus) {
        return exemplaireStatusRepository.save(exemplaireStatus);
    }

    // public Optional<ExemplaireStatus> getLatestStatusForExemplaire(Long exemplaireId) {
    //     return exemplaireStatusRepository.findLatestStatusByExemplaireId(exemplaireId);
    // }

    // public List<ExemplaireStatus> getStatusHistoryForExemplaire(Long exemplaireId) {
    //     return exemplaireStatusRepository.findByExemplaireIdOrderByDateAssociationDesc(exemplaireId);
    // }
}
