package it.epicode.entities.biglietti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "distributori_automatici")
@DiscriminatorValue("DA")
public class DistributoreAutomatico extends Rivenditore {
    private boolean isActive;

    public DistributoreAutomatico(boolean isActive) {
        this.isActive = isActive;
    }

    public DistributoreAutomatico() {
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
