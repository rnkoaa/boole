package com.boole.common.service;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created on 11/12/2015.
 */
public interface MovieService extends BaseService<Movie> {
    Optional<Movie> findWithFullDetails(Long id);

    Page<Movie> findAll(Pageable pageable, Map<String, String> requestParams);

    List<Crew> findActorsForMovie(Long id);

    List<Crew> findWriters(Long id);

    List<Crew> findProducers(Long id);

    List<Crew> findDirectors(Long id);

    List<Role> findRoles(Long id);
}
