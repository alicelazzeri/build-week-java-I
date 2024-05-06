package it.epicode.entities.biglietti;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name ="rivenditori_autorizzati")
@DiscriminatorValue("RA")
public class RivenditoreAutorizzato extends Distributore {
}
