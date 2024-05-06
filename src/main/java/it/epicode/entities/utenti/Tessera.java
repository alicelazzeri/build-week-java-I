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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getInizioValidità() {
        return inizioValidità;
    }

    public void setInizioValidità(LocalDate inizioValidità) {
        this.inizioValidità = inizioValidità;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getFineValidità() {
        return fineValidità;
    }

    public void setFineValidità(LocalDate fineValidità) {
        this.fineValidità = fineValidità;
    }
}
