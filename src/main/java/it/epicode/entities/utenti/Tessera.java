package it.epicode.entities.utenti;

import it.epicode.entities.biglietti.Abbonamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table (name = "tessere")
public class Tessera {
    @Id
    @GeneratedValue
    @Column (name = "numero_tessera")
    private long id;
    private LocalDate inizioValidità;
    private LocalDate fineValidità;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @OneToOne(mappedBy = "tessera")
    private Abbonamento abbonamento;

    @Transient
    private boolean toStringCalled = false;

    public Tessera(Utente ut,LocalDate inizioValidità) {
        this.utente = ut;
        this.inizioValidità = inizioValidità;
        this.fineValidità =  inizioValidità.plusDays(365);
    }

    public Tessera() {
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

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }


    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", inizioValidità=" + inizioValidità +
                ", fineValidità=" + fineValidità +
                '}';
    }



}
