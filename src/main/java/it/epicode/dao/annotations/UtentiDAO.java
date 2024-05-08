package it.epicode.dao.annotations;

import it.epicode.entities.biglietti.Distributore;
import it.epicode.entities.utenti.Tessera;
import it.epicode.entities.utenti.Utente;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtentiDAO {

    private EntityManager em;
    public static final Logger logger = LoggerFactory.getLogger(TitoloViaggioDAO.class);

    public UtentiDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente u){
        try{
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            System.out.println("Utente salvato correttamente");
        }catch (Exception e){
            logger.error("Utente non salvato", e);
            em.getTransaction().rollback();
        }
    }
    public void saveTessera(Tessera t){
        try{
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            System.out.println("Tessera salvata correttamente");
        }catch (Exception e){
            logger.error("Tessera non salvata", e);
            em.getTransaction().rollback();
        }
    }
}
