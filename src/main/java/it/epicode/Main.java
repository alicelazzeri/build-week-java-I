package it.epicode;

import it.epicode.dao.annotations.JpaDao;
import it.epicode.entities.biglietti.Abbonamento;
import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.StatoBiglietto;
import it.epicode.entities.biglietti.TipoAbbonamento;
import it.epicode.entities.mezzi.Tram;
import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        JpaDao jpa = new JpaDao(em);
        Biglietto c = new Biglietto();
        //System.out.println(b);
        jpa.save(c);

        Utente utente = new Utente("Pippo");
        Tessera  tess = new Tessera(utente, LocalDate.of(2023, 05, 12));
        Abbonamento abb = new Abbonamento(utente, tess, TipoAbbonamento.SETTIMANALE);

        jpa.save(utente);
        jpa.save(tess);
        jpa.save(abb);

    }
}