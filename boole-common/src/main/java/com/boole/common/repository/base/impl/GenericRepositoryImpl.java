package com.boole.common.repository.base.impl;

import com.boole.common.repository.base.GenericRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

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

}
