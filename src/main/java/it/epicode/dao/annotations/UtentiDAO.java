package it.epicode.dao.annotations;

import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtentiDAO {

    private EntityManager em;
    public static final Logger logger = LoggerFactory.getLogger(JpaDao.class);
}
