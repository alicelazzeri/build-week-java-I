package it.epicode.entities.biglietti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table (name ="rivenditori_autorizzati")
@DiscriminatorValue("RA")
public class RivenditoreAutorizzato extends Distributore {

    public RivenditoreAutorizzato(TitoloDiViaggio titoloViaggi) {
        super(titoloViaggi);
    }

}
