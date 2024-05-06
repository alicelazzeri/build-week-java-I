package it.epicode.entities.biglietti;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
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
