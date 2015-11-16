package com.boole.common.repository;

import com.boole.common.domain.Movie;
import com.boole.common.repository.base.GenericRepository;


/**
 * Created on 11/12/2015.
 */
public interface MovieRepository extends GenericRepository<Movie, Long> {
    /*@Query(value = "SELECT movie FROM Movie movie LEFT JOIN FETCH movie.genres genre" +
            "LEFT JOIN FETCH movie.roles " +
            "WHERE genre.name in (:genre)",
            countQuery = "SELECT movie FROM Movie movie LEFT JOIN FETCH movie.genres" +
                    "LEFT JOIN FETCH movie.roles " +
                    "WHERE movie.genres.name like :genre")
    Page<Movie> findMovieByGenrePaged(@Param("genre") String genre, Pageable pageable);*/

}
