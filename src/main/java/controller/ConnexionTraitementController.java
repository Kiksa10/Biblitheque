package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import model.Adherent;

import org.springframework.ui.Model;
import service.AuthService;
import service.AdherentService;


@Controller
@SessionAttributes("adherent")
public class ConnexionTraitementController {
    
    @Autowired
    private AuthService authService;

    @Autowired
    private AdherentService adherentService;
    
    @PostMapping("/login")
    public String traitementLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        // Vérification des credentials admin
        if (authService.validateAdmin(username, password)) {
            return "redirect:/adminHome";
        }
        // Vérification des credentials adherent
        else if (authService.validateAdherent(username, password)) {
            Adherent adherent = adherentService.findByUsername(username);
            model.addAttribute("adherent", adherent); // Stocké en session
            return "redirect:/adherentHome";
        }
        // Cas d'erreur
        else {
            model.addAttribute("error", "Identifiant ou mot de passe incorrect");
            return "index";
        }
    }
}