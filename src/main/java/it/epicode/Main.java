package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.dao.annotations.DistributoreDAO;
import it.epicode.dao.annotations.MezziDAO;
import it.epicode.dao.annotations.TitoloViaggioDAO;
import it.epicode.dao.annotations.UtentiDAO;
import it.epicode.entities.biglietti.*;
import it.epicode.entities.mezzi.Autobus;
import it.epicode.entities.mezzi.StatoMezzo;
import it.epicode.entities.mezzi.Tram;
import it.epicode.entities.mezzi.Tratta;
import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jdk.swing.interop.SwingInterOpUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        TitoloViaggioDAO jpa = new TitoloViaggioDAO(em);
        DistributoreDAO disDao = new DistributoreDAO(em);
        UtentiDAO uiDao = new UtentiDAO(em);

        // emettiBiglietto();

        Tratta tratta = new Tratta("Stazione Centrale", "Corso Italia", 20);
        MezziDAO daoMezzi = new MezziDAO(em);
        daoMezzi.saveTratta(tratta);

        Autobus autobus2 = new Autobus(100, StatoMezzo.IN_SERVIZIO,
                LocalDate.of(2023, 3, 12),
                LocalDate.of(2024, 7, 15),
                tratta);
        daoMezzi.saveMezzo(autobus2);

        Tram tram3 = new Tram(120,StatoMezzo.IN_MANUTENZIONE,
                LocalDate.now(),
                LocalDate.of(2025,05,15));
        daoMezzi.saveMezzo(tram3);

        Biglietto bigl = new Biglietto(1234);
        Biglietto bigl2 = new Biglietto(4321);
        Biglietto bigl3 = new Biglietto(5678);
        Biglietto bigl4 = new Biglietto(7890);
        Biglietto bigl5 = new Biglietto(3457);
        Biglietto bigl6 = new Biglietto(3345);
        // daoMezzi.vidimaBiglietto(bigl);
       //  daoMezzi.vidimaBiglietto(bigl3);
        // daoMezzi.vidimaBiglietto(bigl3);

        // jpa.save(bigl6);
        daoMezzi.vidimaBiglietto(bigl6);



    }
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");

    public static void emettiBiglietto() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            do {
                System.out.println("Scegli dove acquistare il biglietto o l'abbonamento:");
                System.out.println("---------------------------");
                System.out.println("1. Rivenditore autorizzato");
                System.out.println("2. Distributore Automatico");
                System.out.println("3. Salve e arrivederci");
                System.out.println("---------------------------");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("RIVENDITORE AUTORIZZATO");
                        System.out.println("---------------------------");
                        System.out.println("1. Emetti biglietto");
                        System.out.println("2. Emetti abbonamento");
                        System.out.println("---------------------------");
                        int subChoice1 = Integer.parseInt(scanner.nextLine());
                        switch (subChoice1) {
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
                        break;
                    case 2:
                        System.out.println("DISTRIBUTORE AUTOMATICO");
                        System.out.println("---------------------------");
                        System.out.println("1. Emetti biglietto");
                        System.out.println("2. Emetti abbonamento");
                        System.out.println("---------------------------");
                        int subChoice2 = Integer.parseInt(scanner.nextLine());
                        switch (subChoice2) {
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
                        break;
                    case 3:
                        System.out.println("--");
                        break;
                    default:
                        System.out.println("Scelta non valida");
                        break;
                }
                System.out.println("Vuoi acquistare altri biglietti o abbonamenti? (sì/no)");
                System.out.println("---------------------------\n");
                input = scanner.nextLine().toLowerCase();
            } while (!input.equals("no"));
        }
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
        DistributoreAutomatico v = new DistributoreAutomatico(b);
        dao.save(v);
    }
    public static void distributoreAbbonamento(){
        Abbonamento a = creaAbbonamento();
        DistributoreDAO dao = new DistributoreDAO(emf.createEntityManager());
        DistributoreAutomatico v = new DistributoreAutomatico(a);
        dao.save(v);
    }

    public static Biglietto emettiBigliettoStandard(){
        Faker f = new Faker();
        Random rand = new Random();
        Biglietto b = new Biglietto(rand.nextInt(100)+1);
        TitoloViaggioDAO dao = new TitoloViaggioDAO(emf.createEntityManager());
        dao.save(b);
        System.out.println(" il numero di biglietto emesso è " + b.getNumeroBiglietto());
        return b;
    }

    public static Abbonamento creaAbbonamento(){
    Scanner scanner = new Scanner(System.in);

        UtentiDAO dao = new UtentiDAO(emf.createEntityManager());
        TitoloViaggioDAO tDao = new TitoloViaggioDAO(emf.createEntityManager());
        System.out.println("Inserisci il nome dell'utente: ");
        String nome = scanner.nextLine();
        Utente u = new Utente(nome);
        dao.save(u);

        Tessera t = new Tessera(u,LocalDate.now());
        dao.saveTessera(t);

        //scanner.nextLine();
        System.out.println("Scegli il tipo di abbonamento: ");
        System.out.println("1. Settimanale");
        System.out.println("2. Mensile");
        int choice = Integer.parseInt(scanner.nextLine());
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