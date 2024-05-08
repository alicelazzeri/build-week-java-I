package it.epicode;

import it.epicode.dao.annotations.DistributoreDAO;
import it.epicode.dao.annotations.TitoloViaggioDAO;
import it.epicode.dao.annotations.UtentiDAO;
import it.epicode.entities.biglietti.*;
import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {



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
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");

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
                    System.out.println("1. Emetti biglietto");
                    System.out.println("2. Emetti abbonamento");
                    choice = scanner.nextInt();
                    switch(choice) {
                        case 1:
                            ricevitoria();
                            break;
                        case 2:
                            creaAbbonamento();
                            break;
                            default:
                                System.out.println("Scelta non valida");
                                break;
                    }
                case 2:
                    System.out.println("1. Emetti biglietto");
                    System.out.println("2. Emetti abbonamento");
                    choice = scanner.nextInt();
                    switch(choice) {
                        case 1:
                            distributore();
                            break;
                        case 2:
                            distributoreAbbonamento();
                            break;
                            default:
                                System.out.println("Scelta non valida");
                                break;
                    }
                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }while (choice != 3);
    }

    public static void ricevitoria(){
        Biglietto b = emettiBigliettoStandard();
        DistributoreDAO dao = new DistributoreDAO(emf.createEntityManager());
        RivenditoreAutorizzato v = new RivenditoreAutorizzato(b);
        dao.save(v);
    }
    public static void ricevitoriaAbbonamento(){
        Abbonamento a = creaAbbonamento();
        DistributoreDAO dao = new DistributoreDAO(emf.createEntityManager());
        RivenditoreAutorizzato v = new RivenditoreAutorizzato(a);
        dao.save(v);
    }



    public static void distributore(){
        Biglietto b = emettiBigliettoStandard();
        DistributoreDAO dao = new DistributoreDAO(emf.createEntityManager());
        DistributoreAutomatico v = new DistributoreAutomatico(b,StatoDistributore.ATTIVO);
        dao.save(v);
    }
    public static void distributoreAbbonamento(){
        Abbonamento a = creaAbbonamento();
        DistributoreDAO dao = new DistributoreDAO(emf.createEntityManager());
        DistributoreAutomatico v = new DistributoreAutomatico(a,StatoDistributore.ATTIVO);
        dao.save(v);
    }

    public static Biglietto emettiBigliettoStandard(){
        Biglietto b = new Biglietto();
        TitoloViaggioDAO dao = new TitoloViaggioDAO(emf.createEntityManager());
        dao.save(b);
        return b;
    }



    public static Abbonamento creaAbbonamento(){
    Scanner scanner = new Scanner(System.in);
        int choice = 0;
        UtentiDAO dao = new UtentiDAO(emf.createEntityManager());
        TitoloViaggioDAO tDao = new TitoloViaggioDAO(emf.createEntityManager());
        System.out.println("Inserisci il nome dell'utente: ");
        String nome = scanner.nextLine();
        Utente u = new Utente(nome);
        dao.save(u);
        Tessera t = new Tessera(u,LocalDate.now());
        dao.saveTessera(t);
        scanner.nextLine();
        System.out.println("Scegli il tipo di abbonamento: ");
        System.out.println("1. Settimanale");
        System.out.println("2. Mensile");
        System.out.println("3. Esci");
        choice = Integer.parseInt(scanner.nextLine());
            TipoAbbonamento sm =switch (choice){
                case 1 -> TipoAbbonamento.SETTIMANALE;
                case 2 -> TipoAbbonamento.MENSILE;
                default -> throw new IllegalArgumentException("Opzione non valida.");
            };
        Abbonamento a = new Abbonamento(u,t,sm);
        tDao.save(a);
        return a;
    }

}