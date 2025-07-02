package model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "exemplaire")
public class Exemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Column(name = "code_barre", unique = true)
    private String codeBarre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_acquisition")
    private Date dateAcquisition = new Date();

    private Double prix;

    private String emplacement;

    @OneToMany(mappedBy = "exemplaire", cascade = CascadeType.ALL)
    private Set<ExemplaireStatus> statusHistory;

    @OneToMany(mappedBy = "exemplaire")
    private Set<Emprunt> emprunts;

    public Exemplaire() {}

    public Exemplaire(Livre livre, String codeBarre) {
        this.livre = livre;
        this.codeBarre = codeBarre;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
    public String getCodeBarre() { return codeBarre; }
    public void setCodeBarre(String codeBarre) { this.codeBarre = codeBarre; }
    public Date getDateAcquisition() { return dateAcquisition; }
    public void setDateAcquisition(Date dateAcquisition) { this.dateAcquisition = dateAcquisition; }
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    public String getEmplacement() { return emplacement; }
    public void setEmplacement(String emplacement) { this.emplacement = emplacement; }
    public Set<ExemplaireStatus> getStatusHistory() { return statusHistory; }
    public void setStatusHistory(Set<ExemplaireStatus> statusHistory) { this.statusHistory = statusHistory; }
    public Set<Emprunt> getEmprunts() { return emprunts; }
    public void setEmprunts(Set<Emprunt> emprunts) { this.emprunts = emprunts; }
}