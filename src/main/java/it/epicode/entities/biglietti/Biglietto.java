package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "biglietti")
@DiscriminatorValue("B")

public class Biglietto extends TitoloDiViaggio {

    private StatoBiglietto statoBiglietto;
    @ManyToOne
    @JoinColumn (name ="id_distributore")
    private Distributore distributore;

    public Biglietto(StatoBiglietto statoBiglietto, Distributore distributore) {
        this.statoBiglietto = statoBiglietto;
        this.distributore = distributore;
    }

    public Biglietto() {

    }
}
