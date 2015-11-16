package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Genre;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/15/15.
 */
public class GenreRepositoryUnitTest extends AbstractBooleCommonTest {

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void testFindOneGenre() {
        Genre genre = genreRepository.findOne(1L);
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo("Action");
    }

    @Test
    public void testFindByGenreName() {
        Genre genre = genreRepository.findByNameIgnoreCase("Action");
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo("Action");
    }

    @Test
    public void testFindGenreLike() {
        Genre genre = genreRepository.findByNameIgnoreCase("action");
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo("Action");
    }

    @Test
    public void testFindFindByNamePage(){
        Page<Genre> genrePage = genreRepository.findDistinctGenreByNameIgnoreCase("action", new PageRequest(0, 10));
        assertThat(genrePage.getTotalElements()).isEqualTo(1);
    }

    /*@Test
    public void testFindByGenreName(){
       Genre genre= genreRepository.findByNameIgnoreCase("Action");
        assertThat(genre).isNotNull();
        assertThat(genre.getName()).isEqualTo("Action");
    }*/
}
