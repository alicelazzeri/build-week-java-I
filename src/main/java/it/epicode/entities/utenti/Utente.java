package it.epicode.entities.utenti;

import jakarta.persistence.*;

@Entity
@Table (name ="utenti")
public class Utente {
    @Id
    @GeneratedValue
    private long id;
    private String nominativo;

    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

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
}
