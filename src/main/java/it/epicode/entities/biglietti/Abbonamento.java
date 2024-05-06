package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "abbonamenti")

public class Abbonamento  extends TitoloDiViaggio {

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

}
