package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpSession;
// import model.Auteur;
import model.Livre;
import model.Reservation;
import model.Adherent;
import model.Emprunt;
import model.Exemplaire;
import model.Abonnement;
import service.AdherentService;
import service.EmpruntService;
import service.ExemplaireService;
import service.AbonnementService;
import service.LivreService;
import service.ReservationService;
import service.AdminService;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private AdherentService adherentService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private ExemplaireService exemplaireService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AbonnementService abonnementService;

    // -------------redirect---------------// 
    @GetMapping("/adminHome")
    public String redirectToAdminPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/adminPage";
    }

    @GetMapping("/admin/dashboard")
    public String redirectAdminPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/adminPage";
    }

    @GetMapping("/admin/pretEnCours")
    public String redirectAdminPagePretEnCours() {
        System.out.println("Redirection vers /livres");
        return "redirect:/adminPretEnCours";
    }

    @GetMapping("/admin/pret/new")
    public String redirectAdminAjoutPret() {
        System.out.println("Redirection vers /adminAjoutPret");
        return "redirect:/adminAjoutPret";
    }

    @GetMapping("/admin/rendu")
    public String redirectAdminRendrePret() {
        System.out.println("Redirection vers /adminAjoutPret");
        return "redirect:/adminRendrePret";
    }

    @GetMapping("/admin/membres")
    public String redirectAdminMembres() {
        System.out.println("Redirection vers /adminAjoutPret");
        return "redirect:/adminMembres";
    }

    @GetMapping("/adherentHome")
    public String redirectToAdherentPage() {
        System.out.println("Redirection vers /livres");
        return "redirect:/adherentPage";
    }

    @GetMapping("/reservation")
    public String redirectToReservationExemplaire() {
        System.out.println("Redirection vers /reservation");
        return "redirect:/reserv";
    }

    @GetMapping("/admin/reservations")
    public String redirectToAdminReservation() {
        System.out.println("Redirection vers /reservationAdmin");
        return "redirect:/reservationAdmin";
    }



   

    //---------------- endpoint ---------------//
    @GetMapping(value = "/endpoint/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAdherentInfo(@PathVariable Long id) {
        try {
            Adherent adherent = adherentService.getAdherentById(id);
            if (adherent == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Adhérent non trouvé avec l'ID: " + id);
            }
            
            // Créez un DTO ou retournez directement l'entité
            // (Attention: retourner l'entité directement peut causer des problèmes de sérialisation)
            Map<String, Object> response = new HashMap<>();
            response.put("id", adherent.getId());
            response.put("nom", adherent.getNom());
            response.put("prenom", adherent.getPrenom());
            response.put("email", adherent.getEmail());
            response.put("telephone", adherent.getTelephone());
            response.put("adresse", adherent.getAdresse());
            response.put("dateInscription", adherent.getDateInscription());
            response.put("actif", adherent.isActif());
            response.put("pretsEnCours", adherent.getPretsEnCours());
            
                return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la récupération des données: " + e.getMessage());
        }
}


      //------------- controller Get---------------//
    @GetMapping("/reservationAdmin")
    @Transactional(readOnly = true)
    public String adminReservationPage(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        List<Reservation> reservations = reservationService.getReservationsEnAttente();
        // System.out.println("livres récupérés : " + reservations);
        model.addAttribute("reservations", reservations);

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de la recuperation des reservation.");
        }

        return "adminReservation";
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


    @GetMapping("/adminMembres")
    @Transactional(readOnly = true)
    public String adminMembres(Model model, @RequestParam(value = "error", required = false) Boolean error) {
        List<Adherent> adherents = adherentService.getAllAdherents();
        System.out.println("adherent récupérés : " + adherents);
        model.addAttribute("membres", adherents);

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout du livres.");
        }

        return "adminGestionMembre";
    }


    @GetMapping("/reserv")
    @Transactional(readOnly = true)
    public String reservationPage(Model model, @RequestParam(value = "error", required = false) Exception error ,@RequestParam(value = "success", required = false) String success) {
        List<Livre> livre = livreService.getAllLivres();
        System.out.println("livres récupérés : " + livre);
        model.addAttribute("livres", livre);

        if (error != null) {
            model.addAttribute("errorMessage", "Une erreur s'est produite lors de l'ajout du livres. "+ error.getMessage());
           
        }
         if (success != null) {
            model.addAttribute("success", success);
        }

        return "reservationExemplaire";
    }


    @GetMapping("/res/{id}")
    @Transactional
    public String reserverLivre(@PathVariable("id") Long id, 
                            Model model, 
                            HttpSession session,
                            @RequestParam Date dateReservation,
                            RedirectAttributes redirectAttributes) {
        
    // Vérification de la session
    Adherent adherent = (Adherent) session.getAttribute("adherent");
    if (adherent == null) {
        redirectAttributes.addFlashAttribute("error", "Vous devez être connecté pour réserver un livre");
        return "redirect:/login";
    }

    // Vérification du livre
    Livre livre = livreService.getLivreWithExemplaires(id);
        if (livre.getNbrExemplaire() == null || livre.getNbrExemplaire() <= 0) {
            redirectAttributes.addFlashAttribute("error", "Plus d'exemplaires disponibles pour ce livre");
            return "redirect:/reserv";
        }
    Abonnement abonnement = abonnementService.getAbonnementById(adherent.getNumeroAdherent());
        if (abonnement == null || !abonnement.isValide()) {
            redirectAttributes.addFlashAttribute("error", "Votre abonnement n'est pas valide");
            return "redirect:/reserv";
        }

    try {
        // Création de la réservation
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setLivre(livre);
        reservation.setDateReservation(dateReservation); // Date actuelle
        
        // Le statut EN_ATTENTE est déjà défini par défaut dans l'entité Reservation
        // Mais on peut le spécifier explicitement pour plus de clarté
        reservation.setStatut(Reservation.StatutReservation.EN_ATTENTE);
        
        // Calcul de la date d'expiration (par exemple 7 jours après)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        reservation.setDateExpiration(calendar.getTime());

        // Sauvegarde de la réservation
        reservationService.saveReservation(reservation);
        
        redirectAttributes.addFlashAttribute("success", "Réservation effectuée avec succès");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Erreur lors de la réservation: " + e.getMessage());
        return "redirect:/reserv";
    }

    return "redirect:/reserv";
}


    @GetMapping("/admin/livres/edit/{id}")
    @Transactional
    public String validerReservation(@PathVariable("id") Long reservationId,
                                    Model model,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {

    

    // Récupération de la réservation
    Reservation reservation = reservationService.getReservationById(reservationId);
    Adherent adherent = reservation.getAdherent();
    Livre livre = reservation.getLivre();
    Abonnement ab = abonnementService.getAbonnementById(adherent.getNumeroAdherent());
    // Vérifications métier
    try {
        // 1. Vérifier que l'adhérent est actif
        if (!adherent.isActif()) {
            throw new IllegalStateException("L'adhérent n'est plus actif");
        }
        if (ab == null || !ab.isValide()) {
            throw new IllegalStateException("L'abonnement de l'adhérent n'est pas valide");
        }

        // 2. Vérifier que l'adhérent n'a pas de pénalité
        if (adherent.getPenalite() != null && adherent.getPenalite() > 0) {
            throw new IllegalStateException("L'adhérent a une pénalité impayée");
        }

        // 3. Vérifier que l'adhérent n'a pas dépassé son quota de prêts
        int maxPrets = adherent.getMaxPrets(); // Supposons que cette propriété existe
        int pretsEnCours = adherentService.countPretsEnCours(adherent.getId());
        if (pretsEnCours >= maxPrets) {
            throw new IllegalStateException("L'adhérent a atteint son quota maximum de prêts");
        }

        // 4. Vérifier qu'il reste des exemplaires disponibles
        if (livre.getNbrExemplaire() <= 0) {
            throw new IllegalStateException("Plus d'exemplaires disponibles pour ce livre");
        }

        // 5. Vérifier que la réservation est toujours en attente
        if (reservation.getStatut() != Reservation.StatutReservation.EN_ATTENTE) {
            throw new IllegalStateException("La réservation n'est plus en attente");
        }

        // 6. Vérifier que la date d'expiration n'est pas dépassée
        if (reservation.getDateExpiration() != null && 
            reservation.getDateExpiration().before(new Date(2025))) {
            throw new IllegalStateException("La réservation a expiré");
        }

        // Si toutes les vérifications sont OK, procéder à la validation
        // 1. Trouver un exemplaire disponible
        if( exemplaireService.getOneExemplaireDispo(livre.getId()) == null){
            throw new IllegalStateException("Plus d'exemplaires disponibles pour ce livre");
        }

        // 2. Mettre à jour les statuts et quantités
        // livre.setNbrExemplaire(livre.getNbrExemplaire() - 1);
        // adherent.setPretsEnCours(adherent.getPretsEnCours() + 1);
        livreService.enleverUnExemplaire(livre.getId());
        adherentService.augmenterNbrPret(adherent.getId());
        reservationService.validerReservation(reservationId);

        redirectAttributes.addFlashAttribute("success", "Réservation validée avec succès");
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Erreur lors de la validation: " + e.getMessage());
        return "redirect:/admin/reservations";
    }

    return "redirect:/admin/reservations";
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
        List<Emprunt> prets = adherentService.getPretsEnCours(adherent.getId());
        
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


    @GetMapping("/adminPretEnCours")
    public String adminPretEnCours(Model model, RedirectAttributes redirectAttributes) {
            // // 4. Préparer les données pour le dashboard
            int nbPrets = adminService.countTotalCurrentEmprunt();
            int nbRetards = adminService.countTotalOverdueEmprunt();
            List<Emprunt> prets = adminService.getAllCurrentEmprunt();
            
            // // 5. Ajouter les attributs au modèle
            
            model.addAttribute("nombrePretsEnCours", nbPrets);
            model.addAttribute("nombreRetards", nbRetards);
            model.addAttribute("prets", prets);

            return "adminPretEnCours";

        }


    @GetMapping("/adminRendrePret")
    public String adminRendrePret(Model model, RedirectAttributes redirectAttributes ,  @RequestParam(value = "error", required = false) Exception error ,@RequestParam(value = "success", required = false) String success) {
            List<Emprunt> prets = empruntService.getAllEmprunts();        
        model.addAttribute("pretsEnCours", prets);
        model.addAttribute("currentDate", LocalDate.now().toString());
         if (error != null) {
            model.addAttribute("error", error.getMessage());
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        return "adminRendrePret";
    }


    @GetMapping("/adminAjoutPret")
    @Transactional(readOnly = true)
    public String adminAjoutPret(Model model,  @RequestParam(value = "error", required = false) Exception error ,@RequestParam(value = "success", required = false) String success) {
        List<Livre> livresDisponibles = livreService.getAllLivres();
        List<Adherent> adherents = adherentService.getAllAdherents();
        
        
        model.addAttribute("livresDisponibles", livresDisponibles);
        model.addAttribute("adherents", adherents);

        if (error != null) {
            model.addAttribute("error", error.getMessage());
        }
        if (success != null) {
            model.addAttribute("success", success);
        }

        return "adminAjoutPret";
    }


    //------------- Controller Post --------------//
    @PostMapping("/adminSaveEmprunt")
    public String savePret(
            @RequestParam("adherentId") Long adherentId,
            @RequestParam("livreId") Long livreId,
            @RequestParam("datePret") Date datePret,
            @RequestParam("dateRetourPrevue") Date dateRetourPrevue,
            @RequestParam(value = "dureePret", defaultValue = "15") int dureePret,
            @RequestParam(value = "commentaire", required = false) String commentaire,
            RedirectAttributes redirectAttributes) {

        try {
            // 1. Vérification de l'adhérent
            Adherent adherent = adherentService.getAdherentById(adherentId);
            Abonnement ab = abonnementService.getAbonnementById(adherent.getNumeroAdherent());

            if (ab == null || !ab.isValide()) {
                redirectAttributes.addFlashAttribute("error", "L'abonnement de l'adhérent n'est pas valide");
                return "redirect:/adminAjoutPret";
            }
           
            if (!adherent.isActif()) {
                redirectAttributes.addFlashAttribute("error", "L'adhérent n'est pas actif");
                return "redirect:/adminAjoutPret";
            }

            if (adherent.getPretsEnCours() >= adherent.getMaxPrets()) {
                redirectAttributes.addFlashAttribute("error", "L'adhérent a atteint son quota maximum de prêts");
                return "redirect:/adminAjoutPret";
            }

            // 2. Vérification du livre
            Livre livre = livreService.getLivreById(livreId);
            Exemplaire exemplair = exemplaireService.getOneExemplaireDispo(livreId);     
            
            if (livre.getNbrExemplaire() <= 0) {
                redirectAttributes.addFlashAttribute("error", "Aucun exemplaire disponible de ce livre");
                return "redirect:/adminAjoutPret";
            }

            // 3. Vérification des dates
            // Date today = Date.valueOf(LocalDate.now());
            // if (datePret.after(today)) {
            //     redirectAttributes.addFlashAttribute("error", "La date de prêt ne peut pas être dans le futur");
            //     return "redirect:/adminAjoutPret";
            // }

            if (dateRetourPrevue.before(datePret)) {
                redirectAttributes.addFlashAttribute("error", "La date de retour doit être après la date de prêt");
                return "redirect:/adminAjoutPret";
            }

            // // 4. Vérification des retards éventuels
            // if (adherentService.aDesRetards(adherentId)) {
            //     redirectAttributes.addFlashAttribute("error", 
            //         "vos avez des retards");
            //     return "redirect:/adminAjoutPret";
                
            // }

            // 5. Création du prêt
            Emprunt pret = new Emprunt();
            pret.setAdherent(adherent);
            pret.setExemplaire(exemplair);
            pret.setDateEmprunt(datePret);
            pret.setDateRetourPrevue(dateRetourPrevue);
            pret.setDateRetourEffectif(null); // Pas encore retourné
            // pret.setStatut("EN_COURS");
            // pret.setCommentaire(commentaire);
            empruntService.saveEmprunt(pret);
            
            // // 6. Mise à jour des données
            livreService.enleverUnExemplaire(livreId);
            adherentService.augmenterNbrPret(adherentId);
            
            // adherent.setPretsEnCours(adherent.getPretsEnCours() + 1);
            // adherentService.saveAdherent(adherent);

           

            redirectAttributes.addFlashAttribute("success", "Le prêt a été enregistré avec succès");
            return "redirect:/adminAjoutPret";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'enregistrement du prêt: " + e.getMessage());
            return "redirect:/adminAjoutPret";
        }
    }
      
    @PostMapping("/processReturn")
    public String processReturn(
            @RequestParam("pretId") Long pretId,
            @RequestParam("dateRetourEffectif") Date dateRetourEffectif,
            @RequestParam("etatLivre") String etatLivre,
            @RequestParam(value = "commentaireRetour", required = false) String commentaireRetour,
            RedirectAttributes redirectAttributes) {

        try {
            // 1. Récupérer le prêt
            Emprunt pret = empruntService.getEmpruntById(pretId);
            
            if (pret == null) {
                redirectAttributes.addFlashAttribute("error", "Prêt introuvable");
                return "/adminRendrePret";
            }

            // 2. Vérifier que le prêt n'est pas déjà rendu
            if (pret.getDateRetourEffectif() != null) {
                redirectAttributes.addFlashAttribute("error", "Ce prêt a déjà été retourné");
                return "/adminRendrePret";
            }

            // 3. Vérification des dates
            // Date today = Date.valueOf(LocalDate.now());
            // if (dateRetourEffectif.after(today)) {
            //     redirectAttributes.addFlashAttribute("error", "La date de retour ne peut pas être dans le futur");
            //     return "/adminRendrePret";
            // }

            // 4. Calculer le retard éventuel
            boolean enRetard = dateRetourEffectif.after(pret.getDateRetourPrevue());
            int joursRetard = enRetard ? 
                (int) ((dateRetourEffectif.getTime() - pret.getDateRetourPrevue().getTime()) / (1000 * 60 * 60 * 24)) : 0;


            Exemplaire exemplaire = pret.getExemplaire();
            Adherent adherent = pret.getAdherent();
            
            // Réactiver l'exemplaire s'il est en bon état
            // if ("BON".equals(etatLivre)) {
            //     exemplaire.setDisponible(true);
            //     exemplaireService.saveExemplaire(exemplaire);
            // }
            
            livreService.ajouterNbrExemplaire(exemplaire.getLivre().getId());
            adherentService.decrementerNbrPret(adherent.getId());
            empruntService.updateEmprunt(pret.getId(), pret.getExemplaire().getId(), dateRetourEffectif);

            // 8. Préparer le message de succès
            String message = "Le retour a été enregistré avec succès";
            if (enRetard) {
                message += ". Attention: retard de " + joursRetard + " jour(s)";
            }
            
            redirectAttributes.addFlashAttribute("success", message);
            return "/adminRendrePret";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'enregistrement du retour: " + e.getMessage());
            return "/test";
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