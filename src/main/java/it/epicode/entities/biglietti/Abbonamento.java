package it.epicode.entities.biglietti;

import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.*;

import java.time.LocalDate;

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

    private LocalDate dataEmissione = LocalDate.now();
    private LocalDate dataScadenza;

    public Abbonamento(Utente utente, Tessera tessera, TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
        this.utente = utente;
        this.tessera = tessera;
        LocalDate dataEmissione = this.dataEmissione;
        LocalDate dataScadenza = scadenzaAbbonamento(tipoAbbonamento);
    }

    public Abbonamento() {
    }

    public LocalDate scadenzaAbbonamento (TipoAbbonamento tipoAbbonamento){
        if (tipoAbbonamento.equals(TipoAbbonamento.SETTIMANALE)) {
            LocalDate settimanale = this.dataEmissione.plusDays(7);
            return settimanale;
        } else {
            LocalDate mensile = this.dataEmissione.plusDays(30);
            return mensile;
        }
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
