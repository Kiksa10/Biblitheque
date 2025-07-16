package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ABONNEMENTS")
public class Abonnement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ABONNEMENT")
    private Long id;
    
    @Column(name = "ADHERENT", nullable = false)
    private String adherent;
    
    @Column(name = "DATE_DEBUT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    
    @Column(name = "DATE_FIN", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    
    @Column(name = "VALIDITE_ABONNEMENT")
    private boolean valide;

    // Constructeurs
    public Abonnement() {
    }

    public Abonnement(String adherent, Date dateDebut, Date dateFin, boolean valide) {
        this.adherent = adherent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.valide = valide;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdherent() {
        return adherent;
    }

    public void setAdherent(String adherent) {
        this.adherent = adherent;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
}