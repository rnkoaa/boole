package com.sakila.repository.common.impl;

import com.sakila.repository.common.GenericRepository;
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

    @Override
    public List<T> findByExample(T example) {
        //Specification<T> spec = ByEntityCriteriaHelper.byExample(this.entityManager, example);
        //return findAll(spec);
        return null;
    }

    @Override
    public List<T> findByExample(T example, Sort sort) {
        //Specification<T> spec = ByEntityCriteriaHelper.byExample(this.entityManager, example);
        //return findAll(spec, sort);
        return null;
    }

    @Override
    public List<T> findBySpecification(Specification<T> specification) {
        return findAll(specification);
    }

}
