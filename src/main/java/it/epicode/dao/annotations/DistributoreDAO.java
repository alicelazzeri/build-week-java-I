package it.epicode.dao.annotations;

import it.epicode.entities.biglietti.Distributore;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributoreDAO {

    private EntityManager em;
    public static final Logger logger = LoggerFactory.getLogger(JpaDao.class);

    public DistributoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Distributore d){
        try{
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
        }catch (Exception e){
            logger.error("Elemento non salvato", e);
            em.getTransaction().rollback();
        }
    }
}
