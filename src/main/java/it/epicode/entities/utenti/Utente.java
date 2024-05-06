package it.epicode.entities.utenti;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name ="utenti")
public class Utente {
    private long numeroTessera;

}
