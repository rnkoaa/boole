package com.boole.common.repository.base;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 10/21/2015.
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    //Optional<T> findOne(ID id);

    List<T> findByExample(T example);

    List<T> findByExample(T example, Sort sort);

    List<T> findBySpecification(Specification<T> specification);

}
