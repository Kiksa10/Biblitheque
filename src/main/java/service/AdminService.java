package service;

import model.Admin;
import model.Emprunt;
import repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public List<Admin> searchAdmins(String searchTerm) {
        return adminRepository.findByNomContainingOrPrenomContainingIgnoreCase(searchTerm, searchTerm);
    }

    public List<Admin> getActiveAdmins() {
        return adminRepository.findByActifTrue();
    }

    // Méthodes spécifiques à l'administration
    
    public List<Emprunt> getAllCurrentEmprunt() {
        return adminRepository.findAllCurrentEmprunts();
    }

    // emprunt en retards 
    public List<Emprunt> getOverdueEmprunt() {
        List<Emprunt> emprunts = adminRepository.findAllOverdueEmprunts();
        LocalDate aujourdhui = LocalDate.now();
        
        emprunts.forEach(emprunt -> {
            LocalDate dateRetour = emprunt.getDateRetourPrevue().toInstant()
                                         .atZone(ZoneId.systemDefault())
                                         .toLocalDate();
            
            // if (dateRetour.isBefore(aujourdhui)) {
            //     emprunt.setStatut(Emprunt.StatutEmprunt.EN_RETARD);
            // }
        });
        
        return emprunts;
    }

    public int countTotalCurrentEmprunt() {
        return adminRepository.countAllCurrentEmprunts();
    }

    public int countTotalOverdueEmprunt() {
        return adminRepository.countAllOverdueEmprunts();
    }

    public void validateLoan(Long empruntId) {
        // Implémentation de la validation d'un emprunt
    }

    public void cancelLoan(Long empruntId) {
        // Implémentation de l'annulation d'un emprunt
    }

    public void sendReminderForOverdueEmprunt() {
        // Implémentation de l'envoi de rappels
    }
}