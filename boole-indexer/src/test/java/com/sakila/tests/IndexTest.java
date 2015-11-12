package com.sakila.tests;

import com.sakila.config.IndexerTestConfig;
import com.sakila.domain.Film;
import com.sakila.index.domain.IndexFilm;
import com.sakila.service.Indexer;
import com.sakila.repository.FilmRepository;
import com.sakila.service.IndexService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class IndexTest extends IndexerTestConfig {
    @Autowired
    IndexService indexService;

    @Autowired
    Indexer<IndexFilm> filmIndexer;

    @Autowired
    FilmRepository filmRepository;

    @Test
    public void testAutowired() {
        assertThat(indexService).isNotNull();
        assertThat(filmIndexer).isNotNull();
        assertThat(filmRepository).isNotNull();
    }

    @Test
    @Transactional
    public void indexSingleItem() {
        Film film = filmRepository.findFilmWithDetails(1);
        assertThat(film).isNotNull();
        System.out.println(film.toString());
        filmIndexer.indexItem(new IndexFilm(film));
    }
}
