package com.boole.common.repository;

import com.boole.common.domain.Movie;
import com.boole.common.repository.base.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created on 11/12/2015.
 */
public interface MovieRepository extends GenericRepository<Movie, Long> {

    //@EntityGraph(value = "movie.genres.graph", type = EntityGraph.EntityGraphType.FETCH)
    @Query(value = "SELECT m FROM Movie m JOIN FETCH m.genres",
            countQuery = "SELECT count(m) FROM Movie m")
    @Transactional(readOnly = true)
    Page<Movie> findMovieWithGenres(Pageable pageable);

}
