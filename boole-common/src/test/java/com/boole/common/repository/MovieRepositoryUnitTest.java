package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Movie;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.boole.common.repository.specifications.MovieSpecifications.movieWithRoles;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created on 11/12/2015.
 */
public class MovieRepositoryUnitTest extends AbstractBooleCommonTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    @Transactional
    public void testFindOneMovie() {
        Movie movie = movieRepository.findOne(1L);
        assertThat(movie).isNotNull();
    }

    @Test
    public void testFindRolesOnMovie() {
       /* List<Role> roles = movieRepository.find
        assertThat(roles).isNotNull();
        assertThat(roles.size()).isGreaterThan(1);
        System.out.println("There are " + roles.size() + " roles associated with the movie");*/
        List<Movie> movies = movieRepository.findBySpecification(movieWithRoles(1L));
        assertThat(movies).isNotEmpty();
        assertThat(movies.size()).isEqualTo(1);
        System.out.println("Retrieved: " + movies.size() + " Movies");

        Movie movie = movies.get(0);
        assertThat(movie.getRoles().size()).isGreaterThan(1);
    }
}
