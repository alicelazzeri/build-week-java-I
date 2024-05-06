package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "biglietti")
@DiscriminatorValue("B")

public class Biglietto extends TitoloDiViaggio {

    private StatoBiglietto statoBiglietto;

    public Biglietto() {

    }
}
