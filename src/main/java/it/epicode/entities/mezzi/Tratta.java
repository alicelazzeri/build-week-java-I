package it.epicode.entities.mezzi;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "tratte")

public class Tratta {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String zonaPartenza;
    @Column
    private String capolinea;
    @Column
    private float avgTempoMedioPercorrenza;

    @OneToMany (mappedBy = "tratta")
    private List<Mezzo> mezzi = new ArrayList<>();

    public Tratta(String zonaPartenza, String capolinea, float avgTempoMedioPercorrenza) {
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
        this.avgTempoMedioPercorrenza = avgTempoMedioPercorrenza;
    }

    public Tratta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public float getAvgTempoMedioPercorrenza() {
        return avgTempoMedioPercorrenza;
    }

    public void setAvgTempoMedioPercorrenza(float avgTempoMedioPercorrenza) {
        this.avgTempoMedioPercorrenza = avgTempoMedioPercorrenza;
    }

    public List<Mezzo> getMezzi() {
        return mezzi;
    }

    public void setMezzi(List<Mezzo> mezzi) {
        this.mezzi = mezzi;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tratta.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("zonaPartenza='" + zonaPartenza + "'")
                .add("capolinea='" + capolinea + "'")
                .add("avgTempoMedioPercorrenza=" + avgTempoMedioPercorrenza)
                .toString();
    }
}
