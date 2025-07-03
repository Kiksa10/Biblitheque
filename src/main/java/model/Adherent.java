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

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private String adresse;

    private String telephone;

    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_inscription")
    private Date dateInscription = new Date();

    @Enumerated(EnumType.STRING)
    private CategorieAdherent categorie;

    private Double cotisation = 0.0;

    private Double penalite = 0.0;

    private Boolean actif = true;

    @OneToMany(mappedBy = "adherent")
    private Set<Emprunt> emprunts;

    @OneToMany(mappedBy = "adherent")
    private Set<Reservation> reservations;

    // Dans votre classe Adherent existante, ajoutez ces champs:
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Adherent() {}

    public Adherent(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Enum pour les catégories
    public enum CategorieAdherent {
        enfant, adulte, senior, etudiant, professionnel;
    }

    // Getters and Setters
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