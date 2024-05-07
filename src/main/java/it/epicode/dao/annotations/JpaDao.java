package it.epicode.dao.annotations;

import it.epicode.dao.DistributoreDao;
import it.epicode.entities.biglietti.Distributore;
import it.epicode.entities.biglietti.TitoloDiViaggio;
import it.epicode.entities.utenti.Tessera;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class JpaDao implements DistributoreDao {

    EntityManager em;
    public static final Logger logger = LoggerFactory.getLogger(JpaDao.class);
    public JpaDao(EntityManager em) {
        this.em = em;
    }

    public void save(Object o){
        try{
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        }catch (Exception e){
            logger.error("Elemento non salvato", e);
            em.getTransaction().rollback();
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



