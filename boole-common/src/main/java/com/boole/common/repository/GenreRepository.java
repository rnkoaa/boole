package com.boole.common.repository;

import com.boole.common.domain.Genre;
import com.boole.common.repository.base.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created on 11/12/2015.
 */
public interface GenreRepository extends GenericRepository<Genre, Long> {

    Page<Genre> findDistinctGenreByNameIgnoreCase(String name, Pageable pageable);

    List<Genre> findDistinctGenreByNameIgnoreCase(String name);

    Page<Genre> findDistinctGenreByNameLike(String name, Pageable pageable);

    List<Genre> findByNameLike(String name);

    Genre findByNameIgnoreCase(String name);
}
