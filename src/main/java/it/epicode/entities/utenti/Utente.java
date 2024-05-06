package it.epicode.entities.utenti;

import jakarta.persistence.*;

@Entity
@Table (name ="utenti")
public class Utente {
    @Id
    @GeneratedValue
    private long id;

    private long numeroTessera;

    private String nominativo;

    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    public Utente(long id, long numeroTessera, String nominativo, Tessera tessera) {
        this.id = id;
        this.numeroTessera = numeroTessera;
        this.nominativo = nominativo;
        this.tessera = tessera;
    }

    public Utente() {
    }

    public long getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(long numeroTessera) {
        this.numeroTessera = numeroTessera;
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
}
