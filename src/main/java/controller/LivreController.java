package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
// import model.Auteur;
import model.Livre;
import model.Adherent;
import model.Emprunt;
import service.AdherentService;
import service.EmpruntService;
// import service.AuteurService;
import service.LivreService;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    // private AuteurService auteurService;

    @GetMapping("/adminHome")
    public String redirectToAdminPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/adminPage";
    }

     @GetMapping("/adherentHome")
    public String redirectToAdherentPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/adherentPage";
    }

    @GetMapping("/adminPage")
    @Transactional(readOnly = true)
    public String adminPage(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        List<Livre> livre = livreService.getAllLivres();
        System.out.println("livres récupérés : " + livre);
        model.addAttribute("livres", livre);

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout du livres.");
        }

        return "dashboardAdmin";
    }


    @GetMapping("/adherentPage")
public String adherentPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    try {
        // 1. Vérification de la session
        if (session.getAttribute("adherent") == null) {
            redirectAttributes.addFlashAttribute("error", "Veuillez vous connecter");
            return "redirect:/login";
        }

        // 2. Récupération de l'adhérent
        Adherent adherent = (Adherent) session.getAttribute("adherent");
        if (adherent == null) {
            session.invalidate();
            redirectAttributes.addFlashAttribute("error", "Session invalide");
            return "redirect:/login";
        }

        // 3. Rafraîchir les données depuis la base si nécessaire
        adherent = adherentService.getAdherentById(adherent.getId());
        if (adherent == null) {
            session.invalidate();
            redirectAttributes.addFlashAttribute("error", "Compte introuvable");
            return "redirect:/login";
        }

        
        // // 4. Préparer les données pour le dashboard
        int nbPrets = adherentService.countPretsEnCours(adherent.getId());
        int nbReservations = adherentService.countReservationsEnAttente(adherent.getId());
        int nbRetards = adherentService.countRetards(adherent.getId());
        String prochainRetour = adherentService.getProchainRetour(adherent.getId());
        List<Emprunt> prets = empruntService.getEmpruntsEnCours(adherent.getId());
        
        // // 5. Ajouter les attributs au modèle
        
        model.addAttribute("nombrePretsEnCours", nbPrets);
        model.addAttribute("nombreReservations", nbReservations);
        model.addAttribute("prochainRetour", prochainRetour);
        model.addAttribute("nombreRetards", nbRetards);
        model.addAttribute("prets", prets);

        return "dashboardAdherent";

    } catch (Exception e) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/login";
    }
}



  
      

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("livre", new Livre());
        // model.addAttribute("auteurs", auteurService.getAllAuteurs());
        return "livres/create";
    }

    @PostMapping
    public String addLivre(@ModelAttribute Livre livre,
                          @RequestParam(value = "auteurIds", required = false) List<Long> auteurIds) {
        try {
            if (auteurIds != null && !auteurIds.isEmpty()) {
                // Set<Auteur> auteurs = new HashSet<>(auteurService.getAuteursByIds(auteurIds));
                // livre.setAuteurs(auteurs);
            }
            livreService.saveLivre(livre);
            return "redirect:/livres";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/livres?error=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Livre livre = livreService.getLivreById(id);
        if (livre == null) {
            return "redirect:/livres";
        }
        model.addAttribute("livre", livre);
        // model.addAttribute("auteurs", auteurService.getAllAuteurs());
        return "livres/edit";
    }

    @PostMapping("/update")
    public String updateLivre(@ModelAttribute("livre") Livre livre,
                            @RequestParam(value = "auteurIds", required = false) List<Long> auteurIds) {
        try {
            if (auteurIds != null && !auteurIds.isEmpty()) {
                // livre.setAuteurs(new HashSet<>(auteurService.getAuteursByIds(auteurIds)));
            } else {
                livre.setAuteurs(new HashSet<>());
            }
            livreService.saveLivre(livre);
            return "redirect:/livres";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/livres?error=true";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLivre(@PathVariable("id") Long id) {
        livreService.deleteLivre(id);
        return "redirect:/livres";
    }
    
}