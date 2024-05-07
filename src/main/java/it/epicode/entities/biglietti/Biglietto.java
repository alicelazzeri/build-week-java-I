package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "biglietti")
@DiscriminatorValue("B")

public class Biglietto extends TitoloDiViaggio {

    @Enumerated(EnumType.STRING)
    private StatoBiglietto statoBiglietto = StatoBiglietto.NON_VIDIMATO ;

    public Biglietto() {

    }
}
