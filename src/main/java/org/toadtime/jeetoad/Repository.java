package org.toadtime.jeetoad;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.toadtime.jeetoad.entity.Toad;

@ApplicationScoped
public class Repository {

    @PersistenceContext
    private EntityManager entityManager;

    public Repository() {
        //default constructor
    }

    @Transactional
    public void saveToad (Toad toad){
        entityManager.persist(toad);
        entityManager.flush();
    }
}
