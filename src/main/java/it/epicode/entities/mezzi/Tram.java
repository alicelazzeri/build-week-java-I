package it.epicode.entities.mezzi;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table (name ="tram")
 @DiscriminatorValue("T")

public class Tram extends Mezzo {

    public Tram(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoServizio, LocalDate finePeriodoServizio, Period periodoServizio, LocalDate inizioPeriodoManutenzione, LocalDate finePeriodoManutenzione, Period periodoManutenzione) {
        super(capienzaMax, statoMezzo, inizioPeriodoServizio, finePeriodoServizio, periodoServizio, inizioPeriodoManutenzione, finePeriodoManutenzione, periodoManutenzione);
    }

    public Tram() {
    }

}
