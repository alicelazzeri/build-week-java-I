package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "abbonamenti")
@DiscriminatorValue("A")

public class Abbonamento extends TitoloDiViaggio {

    @Enumerated (EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;

    @ManyToOne
    @JoinColumn (name ="id_distributore")
    private Distributore distributore;


    public Abbonamento() {
    }

    public Abbonamento(Distributore distributore, TipoAbbonamento tipoAbbonamento) {
        this.distributore = distributore;
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

    public Distributore getDistributore() {
        return distributore;
    }

    public void setDistributore(Distributore distributore) {
        this.distributore = distributore;
    }
}
