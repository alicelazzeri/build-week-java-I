package it.epicode.entities.biglietti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "distributori_automatici")
@DiscriminatorValue("DA")
public class DistributoreAutomatico extends Distributore {
    // private boolean isActive;

    private StatoDistributore statoDistributore;

    public DistributoreAutomatico(TitoloDiViaggio titoloViaggi, StatoDistributore statoDistributore) {
        super(titoloViaggi);
        this.statoDistributore = statoDistributore;
    }

    public DistributoreAutomatico() {
    }

    public StatoDistributore getStatoDistributore() {
        return statoDistributore;
    }

    public void setStatoDistributore(StatoDistributore statoDistributore) {
        this.statoDistributore = statoDistributore;
    }




    //    public DistributoreAutomatico(boolean isActive) {
//        this.isActive = isActive;
//    }
//
//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }
}
