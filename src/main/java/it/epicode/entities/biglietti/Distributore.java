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
    private List<TitoloDiViaggio> titoloViaggio = new ArrayList<>();
    @OneToMany(mappedBy = "distributore")
    private List<Biglietto> biglietto = new ArrayList<>();

    public Distributore(TitoloDiViaggio titoloViaggi) {
        this.titoloViaggio.add(titoloViaggi);
    }
    public Distributore(Biglietto biglietto) {
        this.biglietto.add(biglietto);
    }

    public Distributore(){}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public List<TitoloDiViaggio> getTitoloViaggio() {
        return titoloViaggio;
    }

    public void setTitoloViaggio(List<TitoloDiViaggio> titoloViaggio) {
        this.titoloViaggio = titoloViaggio;
    }

    public String getNome() {
        return null;
    }
}
