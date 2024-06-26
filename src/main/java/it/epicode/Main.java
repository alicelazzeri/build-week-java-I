package it.epicode;

import com.github.javafaker.Faker;
import it.epicode.dao.DistributoreDAO;
import it.epicode.dao.MezziDAO;
import it.epicode.dao.DistributoreDAO;
import it.epicode.dao.MezziDAO;
import it.epicode.dao.TitoloViaggioDAO;
import it.epicode.dao.UtentiDAO;
import it.epicode.entities.biglietti.*;
import it.epicode.entities.mezzi.*;
import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        TitoloViaggioDAO titoloDAO = new TitoloViaggioDAO(em);
        DistributoreDAO disDao = new DistributoreDAO(em);
        UtentiDAO utDao = new UtentiDAO(em);
        MezziDAO daoMezzi = new MezziDAO(em);
        RivenditoreAutorizzato r = new RivenditoreAutorizzato();
        DistributoreAutomatico d = new DistributoreAutomatico();
        Biglietto b = new Biglietto();
        Abbonamento a = new Abbonamento();

        Biglietto bigl7 = new Biglietto(12345, d);
        System.out.println("----------------BIGLIETTO SALVATO--------------");
        titoloDAO.save(bigl7);
        // daoMezzi.vidimaBiglietto(bigl7);

        Tratta tratta = new Tratta("Stazione Centrale", "Corso Italia", 20);
        //daoMezzi.saveTratta(tratta);

        Autobus autobus2 = new Autobus(100, StatoMezzo.IN_SERVIZIO,
                LocalDate.of(2023, 3, 12),
                LocalDate.of(2024, 7, 15),
                tratta);
        daoMezzi.saveMezzo(autobus2);

        Tram tram3 = new Tram(120,StatoMezzo.IN_MANUTENZIONE,
                LocalDate.now(),
                LocalDate.of(2025,05,15));

        daoMezzi.saveMezzo(tram3);

        LocalDate dataIniziale = LocalDate.of(2024, 3, 25);
        LocalDate dataFinale = LocalDate.now();
            getBiglietti(titoloDAO, dataIniziale, dataFinale);

       // emettiBiglietto(r,d,em,scanner,titoloDAO,disDao,utDao,daoMezzi,b,a);

//        var tessera = titoloDAO.cercaAbbonamentoPerTessera(1202);
//        System.out.println("NUMERO TESSERA TROVATA");
//        System.out.println(tessera);
//
//        System.out.println("VALIDITA ABBONAMENTO");
//        validitaAbbonamento(titoloDAO, 1202);

//        var mezziManutenzione = daoMezzi.risultatiMezziPeriodoManutenzione();
//        System.out.println("Mezzi in manutenzione");
//        if (mezziManutenzione != null) {
//            mezziManutenzione.forEach(mezzoManutenzione -> System.out.println(mezzoManutenzione));
//        } else {
//            System.out.println("Nessun mezzo in manutenzione trovato");
//        }
//
//        var mezziServizio =  daoMezzi.risultatiMezziPeriodoServizio();
//        System.out.println("Mezzi in servizio");
//        if (mezziServizio != null) {
//            mezziServizio.forEach(mezzoServizio -> System.out.println(mezzoServizio));
//        } else {
//            System.out.println("Nessun mezzo in manutenzione trovato");
//        }

    }


    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasporto_pubblico");

    public static void emettiBiglietto(Distributore r,Distributore d, EntityManager em,Scanner scanner,
                                       TitoloViaggioDAO titoloDAO,DistributoreDAO disDAO,UtentiDAO utDao,
                                       MezziDAO mezziDao ,Biglietto b,Abbonamento a) {
        try {
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
                                ricevitoria(r,em,titoloDAO,disDAO,b);
                                break;
                            case 2:
                                ricevitoriaAbbonamento(em,scanner,titoloDAO,utDao,disDAO,r,a);
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
                                distributore(d,em,titoloDAO,disDAO,b);
                                break;
                            case 2:
                                distributoreAbbonamento(em,scanner,titoloDAO,utDao,disDAO,d,a);
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
                System.out.println("---------------------------");
                input = scanner.nextLine().toLowerCase();
            } while (!input.equals("no"));

// Ciclo per la convalida dei biglietti o abbonamenti su diversi mezzi di trasporto
            do {
                System.out.println("Prendi mezzo");
                System.out.println("1 - Tram");
                System.out.println("2 - Autobus");
                System.out.println("---------------------------");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("1 - Vidima biglietto");
                        System.out.println("2 - Vidima abbonamento");
                        System.out.println("---------------------------");
                        int subChoice1 = Integer.parseInt(scanner.nextLine());
                        switch (subChoice1) {
                            case 1:
                                vidimaBigliettoTram(mezziDao,b);
                                break;
                            case 2:
                                vidimaAbbonamentoTram(mezziDao,b);
                                break;
                            default:
                                System.out.println("Scelta non valida");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("1 - Vidima biglietto");
                        System.out.println("2 - Vidima abbonamento");
                        System.out.println("---------------------------");
                        int subChoice2 = Integer.parseInt(scanner.nextLine());
                        switch (subChoice2) {
                            case 1:
                                vidimaBigliettoAuto(mezziDao,b);
                                break;
                            case 2:
                                vidimaAbbonamentoAuto(mezziDao,b);
                                break;
                            default:
                                System.out.println("Scelta non valida");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Scelta non valida");
                        break;
                }
                // Aggiungi qui la condizione di uscita dal ciclo, ad esempio:
                System.out.println("Vuoi continuare la convalida dei biglietti o abbonamenti? (sì/no)");
                System.out.println("---------------------------\n");
                input = scanner.nextLine().toLowerCase();
            } while (!input.equals("no"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void ricevitoria(Distributore r, EntityManager em, TitoloViaggioDAO titoloDAO,
                                   DistributoreDAO disDao,Biglietto b){
        b = emettiBigliettoStandard(r,titoloDAO,em,b);
        r = new RivenditoreAutorizzato(b);
        disDao.save(r);
    }
    public static RivenditoreAutorizzato ricevitoriaAbbonamento(EntityManager em, Scanner scanner,TitoloViaggioDAO titoloDAO,UtentiDAO utDAO,DistributoreDAO disDao,Distributore r,Abbonamento a){
        a = creaAbbonamento(em,scanner,titoloDAO,utDAO,r,a);
        RivenditoreAutorizzato v = new RivenditoreAutorizzato(a);
        disDao.save(v);
        return v;
    }

    public static void distributore(Distributore r,EntityManager em,TitoloViaggioDAO titoloDAO,DistributoreDAO disDAO,Biglietto b){
        b = emettiBigliettoStandard(r,titoloDAO,em,b);
        r = new DistributoreAutomatico(b);
        disDAO.save(r);
    }
    public static void distributoreAbbonamento(EntityManager em,Scanner scanner,TitoloViaggioDAO titoloDAO,UtentiDAO utDAO,DistributoreDAO disDAO,Distributore d,Abbonamento a){
        a = creaAbbonamento(em,scanner,titoloDAO,utDAO,d,a);
        DistributoreAutomatico v = new DistributoreAutomatico(a);
        disDAO.save(v);
    }

    public static Biglietto emettiBigliettoStandard(Distributore r,TitoloViaggioDAO titoloDAO,EntityManager em,Biglietto b){
        Faker f = new Faker();
        Random rand = new Random();
        b = new Biglietto(rand.nextInt(100)+1,r);
        titoloDAO.save(b);
        System.out.println(" il numero di biglietto emesso è " + b.getNumeroBiglietto());
        return b;
    }


    public static void vidimaBigliettoTram(MezziDAO mezziDao,Biglietto b){
        mezziDao.vidimaBiglietto(b);
    }
    public static void vidimaBigliettoAuto(MezziDAO mezziDao,Biglietto b){
        mezziDao.vidimaBiglietto(b);
    }

    public static void vidimaAbbonamentoTram(MezziDAO mezziDao,Biglietto b){
        mezziDao.vidimaBiglietto(b);
    }
    public static void vidimaAbbonamentoAuto(MezziDAO mezziDao,Biglietto b){
        mezziDao.vidimaBiglietto(b);
    }



    public static Abbonamento creaAbbonamento(EntityManager em,Scanner scanner, TitoloViaggioDAO titoloDAO,UtentiDAO utDAO,Distributore d,Abbonamento a){
        System.out.println("Inserisci il nome dell'utente: ");
        String nome = scanner.nextLine();
        Utente u = new Utente(nome);
        utDAO.save(u);
        Tessera t = new Tessera(u,LocalDate.now());
        utDAO.saveTessera(t);
        System.out.println("Scegli il tipo di abbonamento: ");
        System.out.println("1. Settimanale");
        System.out.println("2. Mensile");
        int choice = Integer.parseInt(scanner.nextLine());
            TipoAbbonamento sm =switch (choice){
                case 1 -> TipoAbbonamento.SETTIMANALE;
                case 2 -> TipoAbbonamento.MENSILE;
                default -> throw new IllegalArgumentException("Opzione non valida.");
            };
        a = new Abbonamento(u,t,sm,d);
        titoloDAO.save(a);
        return a;
    }

    public static void getBiglietti(TitoloViaggioDAO jpa,LocalDate dataIniziale,LocalDate dataFinale){
        Long risultati = jpa.ricercaTitoliViaggioTotali(dataIniziale, dataFinale);
        System.out.println(risultati);
    }

    public static void validitaAbbonamento (TitoloViaggioDAO titoloDAO, long tessera) {
        Abbonamento ricercaAbbonamento = titoloDAO.cercaAbbonamentoPerTessera(tessera);
        if (LocalDate.now().isAfter(ricercaAbbonamento.getDataScadenza())) {
            System.out.println("L'abbonamento non è valido");
        } else {
            System.out.println("L'abbonamento è valido");
        }
    }


}