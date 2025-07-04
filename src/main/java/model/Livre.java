package model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    private String isbn;

    @Column(name = "annee_publication ")
    private Integer anneePublication;

    private String editeur;

    private String genre;

    @Column(columnDefinition = "TEXT")
    private String resume;

    @Column(name = "nbrExemplaire")
    private Integer nbrExemplaire = 1;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_ajout")
    private Date dateAjout = new Date();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "livre_auteur",
        joinColumns = @JoinColumn(name = "id_livre"),
        inverseJoinColumns = @JoinColumn(name = "id_auteur")
    )
    private Set<Auteur> auteurs;

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL)
    private Set<Exemplaire> exemplaires;

    public Livre() {}

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public Integer getAnneePublication() { return anneePublication; }
    public void setAnneePublication(Integer anneePublication) { this.anneePublication = anneePublication; }
    public String getEditeur() { return editeur; }
    public void setEditeur(String editeur) { this.editeur = editeur; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getResume() { return resume; }
    public void setResume(String resume) { this.resume = resume; }
    public Integer getNbrExemplaire() { return nbrExemplaire; }
    public void setNbrExemplaire(Integer nbrExemplaire) { this.nbrExemplaire = nbrExemplaire; }
    public Date getDateAjout() { return dateAjout; }
    public void setDateAjout(Date dateAjout) { this.dateAjout = dateAjout; }
    public Set<Auteur> getAuteurs() { return auteurs; }
    public void setAuteurs(Set<Auteur> auteurs) { this.auteurs = auteurs; }
    public Set<Exemplaire> getExemplaires() { return exemplaires; }
    public void setExemplaires(Set<Exemplaire> exemplaires) { this.exemplaires = exemplaires; }
}