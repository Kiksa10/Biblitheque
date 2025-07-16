package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_naissance")
    private Date date_naissance;

    private String adresse;

    private String telephone;

    @Column(unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_embauche")
    private Date dateEmbauche = new Date();

    @Enumerated(EnumType.STRING)
    private RoleAdmin role;

    private Boolean actif = true;

    public Admin() {}

    public Admin(String nom, String prenom, String username, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
    }

    // Enum pour les rôles admin
    public enum RoleAdmin {
        SUPER_ADMIN, BIBLIOTHECAIRE, GESTIONNAIRE
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Date getdate_naissance() { return date_naissance; }
    public void setdate_naissance(Date date_naissance) { this.date_naissance = date_naissance; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(Date dateEmbauche) { this.dateEmbauche = dateEmbauche; }
    public RoleAdmin getRole() { return role; }
    public void setRole(RoleAdmin role) { this.role = role; }
    public Boolean getActif() { return actif; }
    public void setActif(Boolean actif) { this.actif = actif; }
}