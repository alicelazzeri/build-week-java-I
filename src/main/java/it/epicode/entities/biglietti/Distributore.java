package it.epicode.entities.biglietti;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "rivenditori")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "dati_distributore", discriminatorType = DiscriminatorType.STRING)

public abstract class Distributore {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "distributore")
    private List<Biglietto> biglietti = new ArrayList<>();

    @OneToMany(mappedBy = "distributore")
    private List<Abbonamento> abbonamenti = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Distributore(Biglietto biglietti, Abbonamento abbonamenti) {
        this.biglietti = (List<Biglietto>) biglietti;
        this.abbonamenti = abbonamenti;
    }
}
