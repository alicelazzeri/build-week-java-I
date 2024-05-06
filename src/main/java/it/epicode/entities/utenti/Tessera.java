package it.epicode.entities.utenti;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table (name = "tessere")
public class Tessera {
    private LocalDate inizioValidità;
    private LocalDate fineValidità;

    public Tessera(LocalDate inizioValidità, LocalDate fineValidità) {
        this.inizioValidità = inizioValidità;
        this.fineValidità =  inizioValidità.plusDays(365);
    }
}
