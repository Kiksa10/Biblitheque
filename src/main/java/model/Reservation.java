package model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_adherent", nullable = false)
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_reservation")
    private Date dateReservation = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date_expiration")
    private Date dateExpiration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('en attente', 'honorée', 'annulée', 'expirée')")
    private StatutReservation statut = StatutReservation.EN_ATTENTE;

    public Reservation() {}

    public Reservation(Adherent adherent, Livre livre) {
        this.adherent = adherent;
        this.livre = livre;
    }

    public Reservation(Adherent adherent, Livre livre, Date dateReservation) {
        this.adherent = adherent;
        this.livre = livre;
        this.dateReservation = dateReservation;
    }

    // Enum pour les statuts de réservation
    public enum StatutReservation {
        EN_ATTENTE("EN_ATTENTE"),
        HONOREE("HONOREE"), 
        ANNULEE("ANNULEE"),
        EXPIREE("EXPIREE");

        private final String dbValue;

        StatutReservation(String dbValue) {
            this.dbValue = dbValue;
        }

        public String getDbValue() {
            return dbValue;
        }

        public static StatutReservation fromDbValue(String dbValue) {
            for (StatutReservation status : values()) {
                if (status.dbValue.equalsIgnoreCase(dbValue)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Statut inconnu: " + dbValue);
        }

        @Override
        public String toString() {
            return this.dbValue;
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Adherent getAdherent() { return adherent; }
    public void setAdherent(Adherent adherent) { this.adherent = adherent; }
    public Livre getLivre() { return livre; }
    public void setLivre(Livre livre) { this.livre = livre; }
    public Date getDateReservation() { return dateReservation; }
    public void setDateReservation(Date dateReservation) { this.dateReservation = dateReservation; }
    public Date getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(Date dateExpiration) { this.dateExpiration = dateExpiration; }
    public StatutReservation getStatut() { return statut; }
    public void setStatut(StatutReservation statut) { this.statut = statut; }
}