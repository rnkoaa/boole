package com.boole.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created on 11/12/2015.
 */
public interface BaseService<T> {

    T findOne(Long id);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(int page, int itemsPerPage);

    List<T> findAll(Specification<T> specification);

    T findOne(Specification<T> specification);
}
