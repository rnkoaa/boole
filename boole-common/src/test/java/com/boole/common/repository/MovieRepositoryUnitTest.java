package com.boole.common.repository;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Movie;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created on 11/12/2015.
 */
@Transactional
public class MovieRepositoryUnitTest extends AbstractBooleCommonTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testFindOneMovie() {
        Movie movie = movieRepository.findOne(1L);
        assertThat(movie).isNotNull();
    }
}
