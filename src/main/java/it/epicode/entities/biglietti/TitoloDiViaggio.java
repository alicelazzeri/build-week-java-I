package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table(name = "titoli_di_viaggio")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "dati_titolo_viaggio", discriminatorType = DiscriminatorType.STRING)

public class TitoloDiViaggio {
    @Id
    @GeneratedValue
    private long id;

    public TitoloDiViaggio(long id) {
        this.id = id;
    }

    public TitoloDiViaggio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
