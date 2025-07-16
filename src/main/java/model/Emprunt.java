package model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "emprunt")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_emprunt")
    private Date dateEmprunt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_retour_prevue")
    private Date dateRetourPrevue;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_retour_effectif")
    private Date dateRetourEffectif;

  
    @Column(name = "statut")
    private String statut = "en cours";

    public Emprunt() {}

    public Emprunt(Adherent adherent, Exemplaire exemplaire) {
        this.adherent = adherent;
        this.exemplaire = exemplaire;
    }

    // Enum pour les statuts d'emprunt
   public enum StatutEmprunt {
    EN_COURS("en cours"),
    RETOURNE("retourne"),
    EN_RETARD("en retard"), 
    PERDU("perdu");  // Ajoutez cette valeur
    
    private final String libelle;
    
    StatutEmprunt(String libelle) {
        this.libelle = libelle;
    }
    
    public String getLibelle() {
        return libelle;
    }
}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }
    public Date getDateEmprunt() { return dateEmprunt; }
    public void setDateEmprunt(Date dateEmprunt) { this.dateEmprunt = dateEmprunt; }
    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    public void setDateRetourPrevue(Date dateRetourPrevue) { this.dateRetourPrevue = dateRetourPrevue; }
    public Date getDateRetourEffectif() { return dateRetourEffectif; }
    public void setDateRetourEffectif(Date dateRetourEffectif) { this.dateRetourEffectif = dateRetourEffectif; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
}