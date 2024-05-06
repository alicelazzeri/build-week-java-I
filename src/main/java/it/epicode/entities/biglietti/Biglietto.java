package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "biglietti")

public class Biglietto extends TitoloDiViaggio {

    @ManyToOne
    @JoinColumn (name ="id_distributore")
    private Distributore distributore;

    public Biglietto(Distributore distributore) {
        this.distributore = distributore;
    }

    public Biglietto() {
    }
}
