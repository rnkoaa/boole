package com.boole.index.service;

import com.boole.index.config.IndexerTestConfig;
import org.elasticsearch.action.search.SearchResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 12/1/15.
 */
public class SearchServiceTests extends IndexerTestConfig {

    @Autowired
    SearchService searchService;

    @Test
    public void testSearch() throws IOException {
        SearchResponse sr = searchService.search("terminator", new HashMap<String, String[]>(), new PageRequest(0, 25));
        assertThat(sr).isNotNull();
        assertThat(sr.getHits()).isNotNull();
        assertThat(sr.getHits().getHits()).isNotNull().isNotEmpty();
    }
}
