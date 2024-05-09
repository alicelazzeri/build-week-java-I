package it.epicode.dao.annotations;

import it.epicode.dao.DistributoreDao;
import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.Distributore;
import it.epicode.entities.biglietti.TitoloDiViaggio;
import it.epicode.entities.mezzi.Mezzo;
import it.epicode.entities.utenti.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
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
        try{
            return em.createQuery("SELECT b FROM Biglietto b where b.numeroBiglietto = :numeroBiglietto", Biglietto.class)
                    .setParameter("numeroBiglietto",numeroBiglietto)
                    .getSingleResult();
        }catch(NoResultException e){
            logger.error("Biglietto non trovato", e);
            return null;
        }
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

    public List<Object[]> ricercaTitoliViaggioTotaliPerDistributore(LocalDate dataIniziale, LocalDate dataFinale) {
        Query query = em.createQuery("SELECT da, COUNT(CASE WHEN b.tipo = 'Abbonamento' THEN 1 END), COUNT(CASE WHEN b.tipo = 'Biglietto' THEN 1 END) " +
                "FROM Biglietto b JOIN b.DistributoreAutomatico da " +
                "WHERE b.dataEmissione BETWEEN :dataIniziale AND :dataFinale " +
                "GROUP BY da");
        query.setParameter("dataIniziale", dataIniziale);
        query.setParameter("dataFinale", dataFinale);
        return query.getResultList();
    }




}



