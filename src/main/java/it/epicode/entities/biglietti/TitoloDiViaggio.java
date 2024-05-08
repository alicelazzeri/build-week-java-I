package it.epicode.entities.biglietti;

import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.*;

@Entity
@Table(name = "titoli_di_viaggio")
@Inheritance (strategy = InheritanceType.JOINED)
@DiscriminatorColumn (name = "dati_titolo_viaggio", discriminatorType = DiscriminatorType.STRING)
//@NamedQuery(name = "RICERCA_TITOLI_VIAGGIO_TOTALI_PER_DISTRIBUTORE",
//        query ="SELECT COUNT(tv) FROM TitoloDiViaggio tv WHERE tv.dataEmissione " +
//                "BETWEEN :dataIniziale AND :dataFinale GROUP BY tv.distributore")
// @NamedQuery(
//    name = "TitoliDiViaggio.findByTipo",
//    query = "SELECT t FROM TitoloDiViaggio t WHERE t.datiTitoloViaggio = 'A' OR t.datiTitoloViaggio = 'B' GROUP BY t.distributore"
//    )
// @NamedQuery(name = "VALIDITÀ_ABBONAMENTO", query = "SELECT abb FROM TitoloDiViaggio.Abbonamento WHERE abb.TipoAbbonamento.MENSILE OR abb.TipoAbbonamento.SETTIMANALE")


public abstract class TitoloDiViaggio {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn (name ="id_distributore")
    private Distributore distributore;

    public TitoloDiViaggio() {

    }

    public TitoloDiViaggio(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
