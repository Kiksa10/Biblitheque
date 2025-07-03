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

// import model.Auteur;
import model.Livre;
// import service.AuteurService;
import service.LivreService;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    // private AuteurService auteurService;

    @GetMapping("/adminHome")
    public String redirectToAdminPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/livresAdmin";
    }

     @GetMapping("/adherentHome")
    public String redirectToAdherentPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/livresAdherent";
    }

    @GetMapping("/livresAdmin")
    @Transactional(readOnly = true)
    public String listLivre(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        List<Livre> livre = livreService.getAllLivres();
        System.out.println("livres récupérés : " + livre);
        model.addAttribute("livres", livre);

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout du livres.");
        }

        return "dashboardAdmin";
    }

    @GetMapping("/livresAdherent")
    @Transactional(readOnly = true)
    public String listExemplaire(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        List<Livre> livre = livreService.getAllLivres();
        System.out.println("livres récupérés : " + livre);
        model.addAttribute("livres", livre);

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout du livres.");
        }

        return "dashboardAdherent";
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