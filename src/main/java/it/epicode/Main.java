package it.epicode;

import it.epicode.dao.annotations.DistributoreDAO;
import it.epicode.dao.annotations.JpaDao;
import it.epicode.entities.biglietti.*;
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
        DistributoreDAO disDao = new DistributoreDAO(em);
       Biglietto c2 = new Biglietto();
//        //System.out.println(b);
       jpa.save(c2);

        Utente utente = new Utente("Pippo");
        Tessera  tess = new Tessera(utente, LocalDate.of(2023, 05, 12));
        Abbonamento abb = new Abbonamento(utente, tess, TipoAbbonamento.SETTIMANALE);

        //DistributoreAutomatico d = new DistributoreAutomatico(abb,StatoDistributore.ATTIVO);
        DistributoreAutomatico d5 = new DistributoreAutomatico(c2,StatoDistributore.ATTIVO);
        disDao.save(d5);
//        jpa.save(utente);
//        jpa.save(tess);
//        jpa.save(abb);

    }
}