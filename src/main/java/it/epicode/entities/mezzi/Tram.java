package it.epicode.entities.mezzi;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table (name ="tram")
@DiscriminatorValue("T")

public class Tram extends Mezzo {

    public Tram(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoServizio, LocalDate finePeriodoServizio, Tratta tratta) {
        super(capienzaMax, statoMezzo, inizioPeriodoServizio, finePeriodoServizio, tratta);
    }

    public Tram(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoManutenzione, LocalDate finePeriodoManutenzione) {
        super(capienzaMax, statoMezzo, inizioPeriodoManutenzione, finePeriodoManutenzione);
    }

    public Tram() {
    }

}
