package it.epicode.entities.mezzi;

import java.time.LocalDate;
import java.time.Period;

public class Autobus extends Mezzo {


    public Autobus(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoServizio, LocalDate finePeriodoServizio, Period periodoServizio, LocalDate inizioPeriodoManutenzione, LocalDate finePeriodoManutenzione, Period periodoManutenzione) {
        super(capienzaMax, statoMezzo, inizioPeriodoServizio, finePeriodoServizio, periodoServizio, inizioPeriodoManutenzione, finePeriodoManutenzione, periodoManutenzione);
    }
    public Autobus(){}

}
