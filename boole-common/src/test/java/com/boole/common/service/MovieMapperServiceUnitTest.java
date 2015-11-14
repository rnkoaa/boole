package com.boole.common.service;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Movie;
import com.boole.common.domain.dto.MovieDTO;
import com.boole.common.service.mapper.MovieMapperService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/13/15.
 */
public class MovieMapperServiceUnitTest extends AbstractBooleCommonTest {

    @Autowired
    MovieService movieService;

    @Autowired
    MovieMapperService movieMapperService;

    @Test
    public void testMapFoundMovie() {
        Optional<Movie> movieOptional = movieService.findOne(1L);
        assertThat(movieOptional).isPresent();
        assertThat(movieOptional.get()).isNotNull();

        Movie movie = movieOptional.get();
        assertThat(movie).isNotNull();
        MovieDTO movieDTO = movieMapperService.mapMovie(movie);

        assertThat(movieDTO).isNotNull();
        assertThat(movieDTO.getId()).isEqualTo(movie.getId());
        System.out.println(movieDTO);
    }

    @Test
    public void testFindDetailsOnMovie() {
        Optional<Movie> movieOptional = movieService.findWithFullDetails(6L);
        assertThat(movieOptional).isPresent();

        Movie movie = movieOptional.get();
        assertThat(movie.getGenres().size()).isGreaterThan(1);
        System.out.println("Movie has " + movie.getGenres().size() + " Genres associated with it");
        assertThat(movie.getRoles().size()).isGreaterThan(1);
        System.out.println("Movie has " + movie.getRoles().size() + " Roles associated with it");

        MovieDTO movieDTO = movieMapperService.mapMovie(movie, "details");

        assertThat(movieDTO).isNotNull();
        assertThat(movieDTO.getGenres().size()).isGreaterThan(1);
        assertThat(movieDTO.getActors().size()).isGreaterThan(1);
    }
}
