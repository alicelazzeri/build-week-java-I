package it.epicode.dao.annotations;

import it.epicode.dao.DistributoreDao;
import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.Distributore;
import it.epicode.entities.biglietti.TitoloDiViaggio;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.utenti.Tessera;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class TitoloViaggioDAO implements DistributoreDao {

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

    public void cercaBiglietto(long numeroBiglietto){
        Biglietto biglietto = em.find(Biglietto.class, numeroBiglietto);
        if (biglietto!= null) {
            logger.debug("biglietto trovato {}", biglietto);
        } else {
            logger.debug("biglietto non trovato");
        }

    }



    @Override
    public void emettiTitoloDiViaggio(TitoloDiViaggio titoloDiViaggio) {

    }

    @Override
    public void conteggioTitoliTotali(TitoloDiViaggio titoloDiViaggio) {

    }

    @Override
    public void conteggioTitoli(Distributore distributore, TitoloDiViaggio titoloDiViaggio, LocalDate inizioRicerca, LocalDate fineRicerca) {

    }

    @Override
    public void validaAbbonamento(Tessera tessera, TitoloDiViaggio titoloDiViaggio) {

    }
}



