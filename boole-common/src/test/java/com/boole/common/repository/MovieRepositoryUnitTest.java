package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Movie;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.boole.common.repository.specifications.MovieSpecifications.*;
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
        List<Movie> movies = movieRepository.findAll(movieWithRolesById(6L));
        assertThat(movies).isNotEmpty();
        assertThat(movies.size()).isEqualTo(1);
        System.out.println("Retrieved: " + movies.size() + " Movies");

        Movie movie = movies.get(0);
        assertThat(movie.getRoles().size()).isGreaterThan(1);
        System.out.println("Movie has " + movie.getRoles().size() + " Roles associated with it");
    }

    @Test
    public void testFindGenresOnMovie() {
        List<Movie> movies = movieRepository.findAll(movieWithGenresById(6L));
        assertThat(movies).isNotEmpty();
        assertThat(movies.size()).isEqualTo(1);
        System.out.println("Retrieved: " + movies.size() + " Movies");

        Movie movie = movies.get(0);
        assertThat(movie.getGenres().size()).isGreaterThan(1);
        System.out.println("Movie has " + movie.getGenres().size() + " Genres associated with it");
    }

    @Test
    public void testFindDetailsOnMovie() {
        List<Movie> movies = movieRepository.findAll(movieWithGenresAndRolesById(6L));
        assertThat(movies).isNotEmpty();
        assertThat(movies.size()).isEqualTo(1);
        System.out.println("Retrieved: " + movies.size() + " Movies");

        Movie movie = movies.get(0);
        assertThat(movie.getGenres().size()).isGreaterThan(1);
        System.out.println("Movie has " + movie.getGenres().size() + " Genres associated with it");
        assertThat(movie.getRoles().size()).isGreaterThan(1);
        System.out.println("Movie has " + movie.getRoles().size() + " Roles associated with it");
    }
}
