package com.boole.tests;

import com.boole.config.IndexerTestConfig;
import com.boole.domain.Film;
import com.boole.index.domain.IndexFilm;
import com.boole.common.service.Indexer;
import com.boole.repository.FilmRepository;
import com.boole.common.service.IndexService;
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
