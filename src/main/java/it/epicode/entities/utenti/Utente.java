package it.epicode.entities.utenti;

import it.epicode.entities.biglietti.Abbonamento;
import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
@Table (name ="utenti")
public class Utente {
    @Id
    @GeneratedValue
    private long id;
    private String nominativo;

    @OneToOne (mappedBy = "utente")
    private Tessera tessera;

    @OneToOne (mappedBy = "utente")
    private Abbonamento abbonamento;

    @Transient
    private boolean toStringCalled = false;

    public Utente(String nominativo) {
        this.nominativo = nominativo;
    }

    public Utente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNominativo() {
        return nominativo;
    }

    public void setNominativo(String nominativo) {
        this.nominativo = nominativo;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }
    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    @Override
    public String toString() {
        return "Utente{}" +
                "id=" + id +
                ", nominativo='" + nominativo + "" +
                ", tessera=" + tessera +
                ", abbonamento=" + abbonamento +
                '}';
    }

}
