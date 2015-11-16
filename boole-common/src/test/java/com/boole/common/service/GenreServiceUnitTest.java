package com.boole.common.service;

import com.boole.common.AbstractBooleCommonTest;
import com.boole.common.domain.Genre;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/15/15.
 */
public class GenreServiceUnitTest extends AbstractBooleCommonTest {
    @Autowired
    GenreService genreService;

    @Test
    public void testFindGenres() {
        Optional<Genre> genreOptional = genreService.findOne(1L);

        assertThat(genreOptional).isPresent();
        assertThat(genreOptional.get().getName()).isNotNull().isNotEmpty();
        assertThat(genreOptional.get().getName()).isEqualTo("Action");

    }

    @Test
    public void testFindNonExistentGenre() {
        Optional<Genre> genreOptional = genreService.findOne(10000L);

        assertThat(genreOptional)
                .is(optionalCondition)
                .isNot(objectPresent);
    }

    private Condition<Optional<Genre>> optionalCondition = new Condition<Optional<Genre>>("optionaCondition") {
        public boolean matches(Optional<Genre> value) {
            return !value.isPresent();
        }
    };

    private Condition<Optional<Genre>> objectPresent = new Condition<Optional<Genre>>("optionaCondition") {
        public boolean matches(Optional<Genre> value) {
            return value.isPresent();
        }
    };
}
