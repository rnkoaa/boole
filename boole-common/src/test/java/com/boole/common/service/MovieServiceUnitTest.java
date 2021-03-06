package com.boole.common.service;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Movie;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/12/15.
 */
public class MovieServiceUnitTest extends AbstractBooleCommonTest {

    @Autowired
    MovieService movieService;

    @Test
    public void testFindOneMovie() {
        Optional<Movie> movieOptional = movieService.findOne(1L);

        assertThat(movieOptional).isPresent();
        Movie movie = movieOptional.get();
        assertThat(movie).isNotNull();
    }

    @Test
    public void testFindItemsWithPaging() {
        Page<Movie> itemsPage = movieService.findAll(0, 20);
        assertThat(itemsPage.getNumber()).isEqualTo(0);
        assertThat(itemsPage.getTotalElements()).isGreaterThan(1);
        System.out.println("Number of Pages: " + itemsPage.getTotalPages());
        System.out.println("Number of Total Elements: " + itemsPage.getTotalElements());
        System.out.println("Number of Elements: " + itemsPage.getNumberOfElements());
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
    }
}
