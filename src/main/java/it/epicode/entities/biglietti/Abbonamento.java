package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "abbonamenti")
@DiscriminatorValue("A")
public class Abbonamento extends TitoloDiViaggio {

    @Enumerated (EnumType.STRING)
    private TipoAbbonamento tipoAbbonamento;

    public Abbonamento() {
    }



    public TipoAbbonamento getTipoAbbonamento() {
        return tipoAbbonamento;
    }

    public void setTipoAbbonamento(TipoAbbonamento tipoAbbonamento) {
        this.tipoAbbonamento = tipoAbbonamento;
    }

}
