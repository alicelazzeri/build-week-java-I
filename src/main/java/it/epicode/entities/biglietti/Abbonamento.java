package it.epicode.entities.biglietti;

import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.*;

@Entity
@Table (name = "abbonamenti")
@DiscriminatorValue("A")
public class Abbonamento extends TitoloDiViaggio {

    @Enumerated (EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;

    @OneToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @OneToOne
    @JoinColumn(name = "id_tessera")
    private Tessera tessera;

    public Abbonamento(Utente utente, Tessera tessera, TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
        this.utente = utente;
        this.tessera = tessera;
    }
    public Abbonamento() {
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

}
