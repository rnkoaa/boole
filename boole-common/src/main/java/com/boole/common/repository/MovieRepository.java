package com.boole.common.repository;

import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import com.boole.common.repository.base.GenericRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created on 11/12/2015.
 */
public interface MovieRepository /*extends JpaRepository<Movie, Long>*/ extends GenericRepository<Movie, Long> {

    // @Query("SELECT movie m JOIN FETCH role r on m.id = r.id JOIN FETCH genre g on g.id = m.id")
    // Page<Movie> findMovieWithAllDetails(Long id, Pageable pageable);
    //@Query("SELECT mov.roles FROM Movie mov WHERE mov.id = (:id)")
    //List<Role> findActors(@Param("id") Long id);
}
