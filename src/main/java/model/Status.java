package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EtatExemplaire etat;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_changement")
    private Date dateChangement = new Date();

    private String notes;

    @OneToMany(mappedBy = "status")
    private Set<ExemplaireStatus> exemplaireStatus;

    public Status() {}

    public Status(EtatExemplaire etat) {
        this.etat = etat;
    }

    // Enum pour les états
    public enum EtatExemplaire {
        DISPONIBLE, EMPRUNTE, RESERVE, EN_REPARATION, PERDU
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EtatExemplaire getEtat() { return etat; }
    public void setEtat(EtatExemplaire etat) { this.etat = etat; }
    public Date getDateChangement() { return dateChangement; }
    public void setDateChangement(Date dateChangement) { this.dateChangement = dateChangement; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Set<ExemplaireStatus> getExemplaireStatus() { return exemplaireStatus; }
    public void setExemplaireStatus(Set<ExemplaireStatus> exemplaireStatus) { this.exemplaireStatus = exemplaireStatus; }
}