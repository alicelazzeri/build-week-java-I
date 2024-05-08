package it.epicode.dao.annotations;

import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.StatoBiglietto;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.mezzi.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MezziDAO {

    private EntityManager em;
    public static final Logger logger = LoggerFactory.getLogger(TitoloViaggioDAO.class);

    public MezziDAO(EntityManager em) {
        this.em = em;
    }

    public void saveMezzo(Mezzo mezzo) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(mezzo);
            trans.commit();
            logger.debug("Mezzo creato correttamente {}", mezzo);
        } catch (Exception e) {
            trans.rollback();
            logger.error("Mezzo non creato", e);
        }
    }

    public void saveTratta (Tratta tratta) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(tratta);
            trans.commit();
            logger.debug("Tratta creata correttamente {}", tratta);
        } catch (Exception e) {
            trans.rollback();
            logger.error("Tratta non creata", e);
        }
    }

    public Biglietto vidimaBiglietto(Biglietto biglietto) {
        EntityTransaction trans = em.getTransaction();
        TitoloViaggioDAO dao = new TitoloViaggioDAO(em);
        dao.cercaBiglietto(biglietto.getNumeroBiglietto());
        try {
            if (biglietto.equals(StatoBiglietto.VIDIMATO)) {
                logger.debug("Biglietto già utilizzato");
            } else {
                biglietto.setStatoBiglietto(StatoBiglietto.VIDIMATO);
                logger.debug("Biglietto per il viaggio corrente vidimato");
                dao.deleteBiglietto(biglietto.getNumeroBiglietto());
                dao.save(biglietto);
            }
            //Query query = em.createQuery("SELECT ");
        } catch(Exception e) {
            logger.error("Errore nella vidimazione del biglietto", e);
        }
        return biglietto;
    }



    // select b from titolidiviaggio where b.stato like 'non_vidimato' replace ["non _vidimato","vidimato]"

}
