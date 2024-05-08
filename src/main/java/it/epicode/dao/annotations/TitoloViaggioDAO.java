package it.epicode.dao.annotations;

import it.epicode.dao.DistributoreDao;
import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.Distributore;
import it.epicode.entities.biglietti.TitoloDiViaggio;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.utenti.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;


public class TitoloViaggioDAO {

    private EntityManager em;
    public static final Logger logger = LoggerFactory.getLogger(TitoloViaggioDAO.class);
    public TitoloViaggioDAO(EntityManager em) {
        this.em = em;
    }

    public void save(TitoloDiViaggio t){
        try{
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            System.out.println("Titolo di viaggio creato correttamente\n");
        }catch (Exception e){
            logger.error("Elemento non salvato", e);
            em.getTransaction().rollback();
        }
        }

    public Biglietto cercaBiglietto(long numeroBiglietto){
        Biglietto biglietto = em.find(Biglietto.class, numeroBiglietto);
        if (biglietto!= null) {
            logger.debug("biglietto trovato {}", biglietto);
        } else {
            logger.debug("biglietto non trovato");
        }
        return biglietto;
    }

    public void deleteBiglietto(long numeroBiglietto) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Biglietto b = cercaBiglietto(numeroBiglietto);
            if (b != null) {
                em.remove(b);
                trans.commit();
                logger.debug("Biglietto eliminaro {}", b);
            } else {
                logger.debug("Biglietto non trovato {}", b);
            }
        } catch (Exception e) {
            trans.rollback();
            logger.error("Errore durante l'eliminazione del biglietto", e);
        }
    }

    public List<TitoloDiViaggio> ricercaTitoliViaggioTotaliPerDistributore(LocalDate dataIniziale, LocalDate dataFinale) {
            Query query = em.createQuery("SELECT COUNT(tv) FROM TitoloDiViaggio tv WHERE tv.dataEmissione BETWEEN :dataIniziale AND :dataFinale GROUP BY tv.distributore", TitoloDiViaggio.class);
            query.setParameter("dataIniziale", dataIniziale);
            query.setParameter("dataFinale", dataFinale);
            var result = query.getResultList();
            return result;
    }
}



