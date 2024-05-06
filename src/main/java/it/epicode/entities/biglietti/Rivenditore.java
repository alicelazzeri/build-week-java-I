package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table (name = "rivenditori")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "data_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Rivenditore {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private TipoAbbonamento tipoAbbonamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
