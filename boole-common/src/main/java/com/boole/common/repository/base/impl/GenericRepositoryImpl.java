package com.boole.common.repository.base.impl;

import com.boole.common.repository.base.GenericRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created on 10/21/2015.
 */
public class GenericRepositoryImpl<T, ID extends Serializable> extends
        SimpleJpaRepository<T, ID> implements GenericRepository<T, ID> {

    private EntityManager entityManager;
    private Class<T> type;


    public GenericRepositoryImpl(
            JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.type = entityInformation.getJavaType();
    }

   /* @PersistenceContext
    private EntityManager entityManager;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public <T extends MyClass> Collection<T> bulkSave(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int i = 0;
        for (T t : entities) {
            savedEntities.add(persistOrMerge(t));
            i++;
            if (i % batchSize == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
        return savedEntities;
    }

    private <T extends MyClass> T persistOrMerge(T t) {
        if (t.getId() == null) {
            entityManager.persist(t);
            return t;
        } else {
            return entityManager.merge(t);
        }
    }*/

}
