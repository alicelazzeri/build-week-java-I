package it.epicode;

import it.epicode.dao.annotations.DistributoreDAO;
import it.epicode.dao.annotations.TitoloViaggioDAO;
import it.epicode.entities.biglietti.*;
import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        TitoloViaggioDAO jpa = new TitoloViaggioDAO(em);
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

        emettiBiglietto();

    }
    public static void emettiBiglietto(){
        Scanner scanner = new Scanner(System.in);
        Abbonamento a = new Abbonamento();
        int choice = 0;
        do {

            System.out.println("Scegli dove emettere il biglietto o l'abbonamento:");
            System.out.println("1. Ricevitoria");
            System.out.println("2. Distributore Automatico");
            System.out.println("3. Esci");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    ricevitoria();
                    break;
                case 2:
                    emettiAbbonamento(scanner,a);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Scelta non valida");
                    break;
            }

            System.out.println("Eetti biglietto");
        }while (choice != 3);
    }

    public static void ricevitoria(){
        Biglietto b = emettiBigliettoStandard();
        DistributoreDAO dao = new DistributoreDAO(emf.createEntityManager());
        RivenditoreAutorizzato v = new RivenditoreAutorizzato(b);
        dao.save(v);
    }

    public static Biglietto emettiBigliettoStandard(){
        Biglietto b = new Biglietto();
        TitoloViaggioDAO dao = new TitoloViaggioDAO(emf.createEntityManager());
        dao.save(b);
        return b;
    }

    public static void emettiAbbonamento(Scanner scanner, Abbonamento a){
        System.out.println("Inserisci un utente");
        Utente u = scanner.next();
        switch (choice) {
            System.out.println("Scegli il tipo di abbonamento: ");
            System.out.println("1. Settimanale");
            System.out.println("2. Mensile");
            System.out.println("3. Esci");
            choice = scanner.nextInt();
            TipoAbbonamento sm =switch (choice){
                case 1 -> TipoAbbonamento.SETTIMANALE;
                case 2 -> TipoAbbonamento.MENSILE;
                default -> throw new IllegalArgumentException("Opzione non valida.");
            };
        }
        emettiAbbonamento( u, t, sm);
        break;
    }
}