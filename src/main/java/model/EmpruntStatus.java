package model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "emprunt_status")
public class EmpruntStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "emprunt_id", nullable = false)
    private Emprunt emprunt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutEmprunt statut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_changement", nullable = false)
    private Date dateChangement = new Date();

    // Enum (peut être déplacé dans un fichier séparé)
    public enum StatutEmprunt {
        EN_COURS("en cours"),
        RETOURNE("retourné"),
        EN_RETARD("en retard"),
        PERDU("perdu");

        private final String libelle;

        StatutEmprunt(String libelle) {
            this.libelle = libelle;
        }

        public String getLibelle() {
            return libelle;
        }
    }

    // Constructeurs
    public EmpruntStatus() {}

    public EmpruntStatus(Emprunt emprunt, StatutEmprunt statut) {
        this.emprunt = emprunt;
        this.statut = statut;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Emprunt getEmprunt() { return emprunt; }
    public void setEmprunt(Emprunt emprunt) { this.emprunt = emprunt; }
    public StatutEmprunt getStatut() { return statut; }
    public void setStatut(StatutEmprunt statut) { this.statut = statut; }
    public Date getDateChangement() { return dateChangement; }
    public void setDateChangement(Date dateChangement) { this.dateChangement = dateChangement; }
}