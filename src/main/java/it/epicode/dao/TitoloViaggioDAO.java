package it.epicode.dao;

import it.epicode.entities.biglietti.Abbonamento;
import it.epicode.entities.biglietti.Biglietto;
import it.epicode.entities.biglietti.TitoloDiViaggio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


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

    public Abbonamento cercaAbbonamentoPerTessera(long tessera) {
        try {
            return em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.id = :tessera", Abbonamento.class)
                    .setParameter("tessera", tessera)
                    .getSingleResult();
        } catch (Exception e) {
            logger.error("Tessera non trovata", e);
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
                logger.debug("Biglietto eliminato {}", b);
            } else {
                logger.debug("Biglietto non trovato {}", b);
            }
        } catch (Exception e) {
            trans.rollback();
            logger.error("Errore durante l'eliminazione del biglietto", e);
        }
    }

    public Long ricercaTitoliViaggioTotali(LocalDate dataIniziale, LocalDate dataFinale) {
        Long query = em.createQuery("SELECT COUNT(b) FROM TitoloDiViaggio b WHERE b.dataEmissione BETWEEN :dataIniziale AND :dataFinale",Long.class)
        .setParameter("dataIniziale", dataIniziale)
        .setParameter("dataFinale", dataFinale)
        .getSingleResult();
        return query;
    }

//    List<Object[]> risultatiBigliettiPerPuntoEmissione (LocalDate dataIniziale, LocalDate dataFinale) {
//        em.createQuery(
//                        "SELECT b.distributore.id, COUNT(b) FROM Biglietto b " +
//                                "WHERE b.dataEmissione BETWEEN :dataIniziale AND :dataFinale " +
//                                "GROUP BY b.distributore.id", Object[].class)
//                .setParameter("dataIniziale", dataIniziale)
//                .setParameter("dataFinale", dataFinale)
//                .getResultList();
//    }

}



