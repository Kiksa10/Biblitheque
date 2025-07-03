package service;

import model.Admin;
import model.Adherent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.AdherentRepository;
import repository.AdminRepository;


@Service
public class AuthService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private AdherentRepository adherentRepository;
    
    public boolean validateAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        return admin != null && admin.getPassword().equals(password);
    }
    
    public boolean validateAdherent(String username, String password) {
        Adherent user = adherentRepository.findByUsername(username).orElse(null);
        return user != null && user.getPassword().equals(password);
    }
}