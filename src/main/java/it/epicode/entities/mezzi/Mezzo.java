package it.epicode.entities.mezzi;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table (name = "mezzi")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "dati_mezzo", discriminatorType = DiscriminatorType.STRING)
public abstract class Mezzo {
    @Id
    @GeneratedValue
    private long id;

    private int capienzaMax;
    private StatoMezzo statoMezzo;
    private LocalDate inizioPeriodoServizio;
    private LocalDate finePeriodoServizio;
    private Period periodoServizio;
    private LocalDate inizioPeriodoManutenzione;
    private LocalDate finePeriodoManutenzione;
    private Period periodoManutenzione;

    public Mezzo(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoServizio, LocalDate finePeriodoServizio, Period periodoServizio, LocalDate inizioPeriodoManutenzione, LocalDate finePeriodoManutenzione, Period periodoManutenzione) {
        this.capienzaMax = capienzaMax;
        this.statoMezzo = statoMezzo;
        this.inizioPeriodoServizio = inizioPeriodoServizio;
        this.finePeriodoServizio = finePeriodoServizio;
        this.periodoServizio = Period.between(inizioPeriodoServizio, finePeriodoServizio);
        this.inizioPeriodoManutenzione = inizioPeriodoManutenzione;
        this.finePeriodoManutenzione = finePeriodoManutenzione;
        this.periodoManutenzione = Period.between(inizioPeriodoManutenzione, finePeriodoManutenzione);
    }

    public Mezzo() {
    }

}
