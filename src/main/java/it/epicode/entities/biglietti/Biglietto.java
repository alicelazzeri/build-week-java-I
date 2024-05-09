package it.epicode.entities.biglietti;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "biglietti")
@DiscriminatorValue("B")
public class Biglietto extends TitoloDiViaggio {

    @Enumerated(EnumType.STRING)
    private StatoBiglietto statoBiglietto;
    private LocalDate dataEmissione;
    private long numeroBiglietto;



    public Biglietto(long numeroBiglietto, Distributore distributore) {
        super(distributore);
        this.numeroBiglietto = numeroBiglietto;
        this.dataEmissione = LocalDate.now();
        this.statoBiglietto = StatoBiglietto.NON_VIDIMATO;
    }



    public Biglietto() {
    }
    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
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
