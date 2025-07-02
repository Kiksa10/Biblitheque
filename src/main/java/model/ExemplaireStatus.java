package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exemplaire_status")
public class ExemplaireStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_association")
    private Date dateAssociation = new Date();

    public ExemplaireStatus() {}

    public ExemplaireStatus(Exemplaire exemplaire, Status status) {
        this.exemplaire = exemplaire;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Exemplaire getExemplaire() { return exemplaire; }
    public void setExemplaire(Exemplaire exemplaire) { this.exemplaire = exemplaire; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Date getDateAssociation() { return dateAssociation; }
    public void setDateAssociation(Date dateAssociation) { this.dateAssociation = dateAssociation; }
}