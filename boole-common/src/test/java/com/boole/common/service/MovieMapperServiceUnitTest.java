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
        //AssertionsForInterfaceTypes.assertThat(movie.getGenres()).isEmpty();
    }
}
