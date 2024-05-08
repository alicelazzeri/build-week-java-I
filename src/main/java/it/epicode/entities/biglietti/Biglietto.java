package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "biglietti")
@DiscriminatorValue("B")

public class Biglietto extends TitoloDiViaggio {

    @Enumerated(EnumType.STRING)
    private StatoBiglietto statoBiglietto = StatoBiglietto.NON_VIDIMATO;

    private long numeroBiglietto;

    public Biglietto(long numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
        StatoBiglietto statoBiglietto1 = this.statoBiglietto;
    }

    public Biglietto() {
    }

    public StatoBiglietto getStatoBiglietto() {
        return statoBiglietto;
    }

    public void setStatoBiglietto(StatoBiglietto statoBiglietto) {
        this.statoBiglietto = statoBiglietto;
    }

    public long getNumeroBiglietto() {
        return numeroBiglietto;
    }

    public void setNumeroBiglietto(long numeroBiglietto) {
        this.numeroBiglietto = numeroBiglietto;
    }
}
