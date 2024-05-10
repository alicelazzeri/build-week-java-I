package it.epicode.entities.mezzi;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table (name = "mezzi")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "dati_mezzo", discriminatorType = DiscriminatorType.STRING)
public abstract class Mezzo {
    @Id
    @GeneratedValue
    private long id;

    private int capienzaMax;

    @Enumerated(EnumType.STRING)
    private StatoMezzo statoMezzo;
    private LocalDate inizioPeriodoServizio;
    private LocalDate finePeriodoServizio;
    private Period periodoServizio;
    private LocalDate inizioPeriodoManutenzione;
    private LocalDate finePeriodoManutenzione;
    private Period periodoManutenzione;

    @ManyToOne
    @JoinColumn(name ="id_tratta")
    private Tratta tratta;



    public Mezzo(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoServizio,
                    LocalDate finePeriodoServizio,Tratta tratta) {
        this.capienzaMax = capienzaMax;
        this.statoMezzo = statoMezzo;
        this.inizioPeriodoServizio = inizioPeriodoServizio;
        this.finePeriodoServizio = finePeriodoServizio;
        this.periodoServizio = Period.between(inizioPeriodoServizio, finePeriodoServizio);
        this.inizioPeriodoManutenzione = LocalDate.of(1,1,1);
        this.finePeriodoManutenzione = LocalDate.of(1,1,1);
        this.periodoManutenzione = Period.between(inizioPeriodoManutenzione, finePeriodoManutenzione);
        this.tratta = tratta;
    }

    public Mezzo(int capienzaMax, StatoMezzo statoMezzo, LocalDate inizioPeriodoManutenzione,
                 LocalDate finePeriodoManutenzione) {
        this.statoMezzo = statoMezzo;
        this.inizioPeriodoManutenzione = inizioPeriodoManutenzione;
        this.finePeriodoManutenzione = finePeriodoManutenzione;
        this.periodoManutenzione = Period.between(inizioPeriodoManutenzione, finePeriodoManutenzione);
        this.inizioPeriodoServizio = LocalDate.of(1,1,1);
        this.finePeriodoServizio = LocalDate.of(1,1,1);
        this.periodoServizio = Period.between(inizioPeriodoServizio, finePeriodoServizio);
        this.tratta = tratta;
    }

    public Mezzo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapienzaMax() {
        return capienzaMax;
    }

    public void setCapienzaMax(int capienzaMax) {
        this.capienzaMax = capienzaMax;
    }

    public StatoMezzo getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(StatoMezzo statoMezzo) {
        this.statoMezzo = statoMezzo;
    }

    public LocalDate getInizioPeriodoServizio() {
        return inizioPeriodoServizio;
    }

    public void setInizioPeriodoServizio(LocalDate inizioPeriodoServizio) {
        this.inizioPeriodoServizio = inizioPeriodoServizio;
    }

    public LocalDate getFinePeriodoServizio() {
        return finePeriodoServizio;
    }

    public void setFinePeriodoServizio(LocalDate finePeriodoServizio) {
        this.finePeriodoServizio = finePeriodoServizio;
    }

    public Period getPeriodoServizio() {
        return periodoServizio;
    }

    public void setPeriodoServizio(Period periodoServizio) {
        this.periodoServizio = periodoServizio;
    }

    public LocalDate getInizioPeriodoManutenzione() {
        return inizioPeriodoManutenzione;
    }

    public void setInizioPeriodoManutenzione(LocalDate inizioPeriodoManutenzione) {
        this.inizioPeriodoManutenzione = inizioPeriodoManutenzione;
    }

    public LocalDate getFinePeriodoManutenzione() {
        return finePeriodoManutenzione;
    }

    public void setFinePeriodoManutenzione(LocalDate finePeriodoManutenzione) {
        this.finePeriodoManutenzione = finePeriodoManutenzione;
    }

    public Period getPeriodoManutenzione() {
        return periodoManutenzione;
    }

    public void setPeriodoManutenzione(Period periodoManutenzione) {
        this.periodoManutenzione = periodoManutenzione;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    @Override
    public String toString(){
        return String.format("Mezzo: [id=%s, statoMezzo=%s, inizioPeriodoServizio=%s, finePeriodoServizio=%s," +
                        " periodoServizio=%s,inizioPeriodoManutenzione=%s, finePeriodoManutenzione=%s,periodoManutenzione=%s]",
                id, statoMezzo, inizioPeriodoServizio, finePeriodoServizio,periodoServizio,
                inizioPeriodoManutenzione,finePeriodoManutenzione,periodoManutenzione );
    }
}
