package model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "adherent")
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    @Column(length = 20)
    private String telephone;

    @Column(unique = true, length = 255)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_inscription", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date dateInscription = new Date();

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('enfant', 'adulte', 'senior', 'etudiant', 'professionnel')", nullable = false)
    private CategorieAdherent categorie;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double cotisation = 0.0;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double penalite = 0.0;

    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean actif = true;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name = "numero_adherent", unique = true, length = 20)
    private String numeroAdherent;

    @Column(name = "max_prets", columnDefinition = "INT DEFAULT 5")
    private Integer maxPrets = 5;

    @Column(name = "prets_en_cours", columnDefinition = "INT DEFAULT 0")
    private Integer pretsEnCours = 0;

    @Column(name = "date_dernier_pret")
    @Temporal(TemporalType.DATE)
    private Date dateDernierPret;

    @OneToMany(mappedBy = "adherent")
    private Set<Emprunt> emprunts;

    @OneToMany(mappedBy = "adherent")
    private Set<Reservation> reservations;

    public Adherent() {}

    public Adherent(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Adherent(String nom, String prenom, Date dateNaissance, String adresse, String telephone, String email,
                    CategorieAdherent categorie, Double cotisation, Double penalite, Boolean actif,
                    String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.categorie = categorie;
        this.cotisation = cotisation;
        this.penalite = penalite;
        this.actif = actif;
        this.username = username;
        this.password = password;
    }

    public enum CategorieAdherent {
        enfant, adulte, senior, etudiant, professionnel
    }

    // Getters and Setters
    public String getNumeroAdherent() { return numeroAdherent; }
    public void setNumeroAdherent(String numeroAdherent) { this.numeroAdherent = numeroAdherent; }
    public Integer getMaxPrets() { return maxPrets; }
    public void setMaxPrets(Integer maxPrets) { this.maxPrets = maxPrets; }
    public Integer getPretsEnCours() { return pretsEnCours; }
    public void setPretsEnCours(Integer pretsEnCours) { this.pretsEnCours = pretsEnCours; }
    public Date getDateDernierPret() { return dateDernierPret; }
    public Boolean isActif() { return actif; }
    public void setDateDernierPret(Date dateDernierPret) { this.dateDernierPret = dateDernierPret; }
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Date getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getDateInscription() { return dateInscription; }
    public void setDateInscription(Date dateInscription) { this.dateInscription = dateInscription; }
    public CategorieAdherent getCategorie() { return categorie; }
    public void setCategorie(CategorieAdherent categorie) { this.categorie = categorie; }
    public Double getCotisation() { return cotisation; }
    public void setCotisation(Double cotisation) { this.cotisation = cotisation; }
    public Double getPenalite() { return penalite; }
    public void setPenalite(Double penalite) { this.penalite = penalite; }
    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }
    public Set<Emprunt> getEmprunts() { return emprunts; }
    public void setEmprunts(Set<Emprunt> emprunts) { this.emprunts = emprunts; }
    public Set<Reservation> getReservations() { return reservations; }
    public void setReservations(Set<Reservation> reservations) { this.reservations = reservations; }
        // Et les getters/setters correspondants
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    
}