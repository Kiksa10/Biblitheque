package model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSortie;

    @ManyToOne
    @JoinColumn(name = "idType", nullable = false)
    private Type type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "categorieFilm",
            joinColumns = @JoinColumn(name = "idFilm"),
            inverseJoinColumns = @JoinColumn(name = "idCategorie")
    )
    private Set<Categorie> categories;

    public Film() {}

    public Film(Long id, String titre, Date dateSortie, Type type) {
        this.id = id;
        this.titre = titre;
        this.dateSortie = dateSortie;
        this.type = type;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public Date getDateSortie() { return dateSortie; }
    public void setDateSortie(Date dateSortie) { this.dateSortie = dateSortie; }

    public Type getType() { return type; }
    public void setType(Type type) { this.type = type; }

    public Set<Categorie> getCategories() { return categories; }
    public void setCategories(Set<Categorie> categories) { this.categories = categories; }
}