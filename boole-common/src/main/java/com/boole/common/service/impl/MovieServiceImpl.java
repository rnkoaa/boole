package com.boole.common.service.impl;

import com.boole.common.domain.Movie;
import com.boole.common.repository.MovieRepository;
import com.boole.common.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/12/15.
 */
@Service
@Transactional(readOnly = true)
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie findOne(Long id) {
        logger.debug("Finding Movies with id: {}", id);
        return movieRepository.findOne(id);
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        logger.debug("Finding Movies with paging");
        return movieRepository.findAll(pageable);
    }

    @Override
    public Page<Movie> findAll(int page, int itemsPerPage) {
        logger.debug("Finding Movies with page {} and number of Items: {}", page, itemsPerPage);
        Pageable pageable = createPageable(page, itemsPerPage, null);
        return findAll(pageable);
    }

    @Override
    public List<Movie> findAll(Specification<Movie> specification) {
        logger.debug("Finding Movies with Using Specification");
        return movieRepository.findBySpecification(specification);
    }

    @Override
    public Movie findOne(Specification<Movie> specification) {
        return null;
    }
}
