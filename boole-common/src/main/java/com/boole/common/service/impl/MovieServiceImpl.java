package com.boole.common.service.impl;

import com.boole.common.domain.Crew;
import com.boole.common.domain.Movie;
import com.boole.common.domain.Role;
import com.boole.common.repository.MovieRepository;
import com.boole.common.repository.RoleRepository;
import com.boole.common.repository.specifications.RoleSpecifications;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.boole.common.repository.specifications.MovieSpecifications.movieWithGenresAndRolesById;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/12/15.
 */
@Service
@Transactional(readOnly = true)
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, RoleRepository roleRepository) {
        this.movieRepository = movieRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Movie> findOne(Long id) {
        logger.debug("Finding Movies with id: {}", id);
        Movie movie = movieRepository.findOne(id);
        return Optional.ofNullable(movie);
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
        return movieRepository.findAll(specification);
    }

    @Override
    public Movie findOne(Specification<Movie> specification) {
        return null;
    }

    @Override
    public Optional<Movie> findWithFullDetails(Long id) {
        logger.debug("Finding Movies with Using Specification");
        Movie movie = movieRepository.findOne(movieWithGenresAndRolesById(id));
        return Optional.ofNullable(movie);
    }

    @Override
    public List<Crew> findActorsForMovie(Long id) {
        logger.debug("Finding Actors for movie with id: {}", id);
        List<Role> roles = roleRepository.findAll(RoleSpecifications.rolesForMovie(id, "Cast"));

        return getCrews(roles);
    }

    @Override
    public List<Crew> findWriters(Long id) {
        logger.debug("Finding Writers for movie with id: {}", id);
        List<Role> roles = roleRepository.findAll(RoleSpecifications.rolesForMovie(id, "Writer"));
        return getCrews(roles);
    }

    @Override
    public List<Crew> findProducers(Long id) {
        logger.debug("Finding Producers for movie with id: {}", id);
        List<Role> roles = roleRepository.findAll(RoleSpecifications.rolesForMovie(id, "Producer"));
        return getCrews(roles);
    }

    @Override
    public List<Crew> findDirectors(Long id) {
        logger.debug("Finding Directors for movie with id: {}", id);
        List<Role> roles = roleRepository.findAll(RoleSpecifications.rolesForMovie(id, "Director"));
        return getCrews(roles);
    }

    @Override
    public List<Role> findRoles(Long id) {
        logger.debug("Finding all roles for movie with id: {}", id);
        return roleRepository.findAll(RoleSpecifications.rolesForMovie(id));
    }

    private List<Crew> getCrews(List<Role> roles) {
        return roles.stream()
                .map(Role::getCrew)
                .collect(Collectors.toList());
    }

}
