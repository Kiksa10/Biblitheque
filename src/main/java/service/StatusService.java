package service;

import model.Status;
import repository.StatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;

    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    // public Optional<Status> getStatusByEtat(Status.EtatExemplaire etat) {
    //     return statusRepository.findByEtat(etat);
    // }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }
}