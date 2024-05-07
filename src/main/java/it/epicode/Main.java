package it.epicode;

import it.epicode.dao.annotations.JpaDao;
import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.StatoBiglietto;
import it.epicode.entities.mezzi.Tram;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        JpaDao jpa = new JpaDao(em);
        Biglietto c = new Biglietto();
        //System.out.println(b);
        jpa.save(c);
    }
}