package it.epicode.entities.utenti;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate inizioValidità;
    private LocalDate fineValidità;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    public Tessera(LocalDate inizioValidità, LocalDate fineValidità) {
        this.inizioValidità = inizioValidità;
        this.fineValidità =  inizioValidità.plusDays(365);
    }
}
