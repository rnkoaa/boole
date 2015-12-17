package com.boole.index.service;

import com.boole.index.config.IndexerTestConfig;
import org.assertj.core.api.Condition;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 12/1/15.
 */
public class SearchServiceTests extends IndexerTestConfig {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceTests.class);
    @Autowired
    SearchService searchService;

    @Autowired
    private Client client;

    SearchServiceUtils searchServiceUtils = SearchServiceUtils.searchServiceUtils;

    @Test
    public void testFilterExtraction() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        Map<String, String[]> filters = searchServiceUtils.extractFilters(requestParams);
        assertThat(filters).isEmpty();
        assertThat(filters.size()).isEqualTo(0);
    }

    @Test
    public void testFilterWithSingleFilterExtraction() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        requestParams.put("directors", "John Botti");
        Map<String, String[]> filters = searchServiceUtils.extractFilters(requestParams);
        assertThat(filters).isNotEmpty();
        assertThat(filters.size()).isEqualTo(1);
    }

    @Test
    public void testFilterWithTwoFiltersFilterExtraction() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        requestParams.put("directors", "John Botti");
        requestParams.put("producers", "John Botti");
        Map<String, String[]> filters = searchServiceUtils.extractFilters(requestParams);
        assertThat(filters).isNotEmpty();
        assertThat(filters.size()).isEqualTo(2);
    }

    @Test
    public void testCommaSeparatedFilters() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        requestParams.put("directors", "John Botti,Erik Fleming");
        requestParams.put("producers", "John Botti");
        Map<String, String[]> filters = searchServiceUtils.extractFilters(requestParams);

        assertThat(filters)
                .isNotEmpty()
                .has(new Condition<Map<String, String[]>>() {
                    public boolean matches(Map<String, String[]> value) {
                        return value.size() == 2;
                    }
                });

        String[] directorFilters = filters.get("directors");
        assertThat(directorFilters)
                .isNotEmpty()
                .has(new Condition<String[]>() {

                    @Override
                    public boolean matches(String[] value) {
                        return value.length == 2;
                    }
                });

        searchService.buildQuery("terminator", filters);
    }

    public static SearchRequestBuilder movieSearchRequestBuilder(Client client) {
        return client.prepareSearch("boole").setTypes("movies");
    }

    @Test
    public void testSearch() throws IOException {
        SearchResponse sr = searchService.search("terminator", new HashMap<>(), new PageRequest(0, 25));
        assertThat(sr).isNotNull();
        assertThat(sr.getHits()).isNotNull();
        assertThat(sr.getHits().getHits()).isNotNull().isNotEmpty();
    }
}
