package com.boole.common.service;

import com.boole.common.domain.Movie;

import java.util.Optional;

/**
 * Created on 11/12/2015.
 */
public interface MovieService extends BaseService<Movie> {
    Optional<Movie> findWithFullDetails(Long id);
}
