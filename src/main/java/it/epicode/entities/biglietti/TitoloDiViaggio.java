package it.epicode.entities.biglietti;

import jakarta.persistence.*;

@Entity
@Table(name = "titoli_di_viaggio")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "dati_titolo_viaggio", discriminatorType = DiscriminatorType.STRING)

public abstract class TitoloDiViaggio {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn (name ="id_distributore")
    private Distributore distributore;




    public TitoloDiViaggio() {
    }

    public TitoloDiViaggio(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
