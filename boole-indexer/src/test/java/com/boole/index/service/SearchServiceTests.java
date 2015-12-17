package com.boole.index.service;

import com.boole.index.config.IndexerTestConfig;
import org.assertj.core.api.Condition;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static List<String> KNOWN_FILTERS = new ArrayList<>(5);

    static {
        KNOWN_FILTERS.add("directors");
        KNOWN_FILTERS.add("producers");
        KNOWN_FILTERS.add("writers");
        KNOWN_FILTERS.add("genres");
        KNOWN_FILTERS.add("actors");
    }

    private Map<String, String[]> extractFilters(Map<String, String> requestParams) {
        logger.debug("Request Params --------------------------------------------");
        Map<String, String[]> results = new HashMap<>();
        requestParams.entrySet()
                .forEach(entry -> {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    logger.debug("Key: {}, Value: {}", key, value);

                    if (KNOWN_FILTERS.contains(key)) {
                        String[] filters = value.split(",");
                        results.put(key, filters);
                    }
                });
        return results;
    }

    @Test
    public void testFilterExtraction() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        Map<String, String[]> filters = extractFilters(requestParams);
        assertThat(filters).isEmpty();
        assertThat(filters.size()).isEqualTo(0);
    }

    @Test
    public void testFilterWithSingleFilterExtraction() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        requestParams.put("directors", "John Botti");
        Map<String, String[]> filters = extractFilters(requestParams);
        assertThat(filters).isNotEmpty();
        assertThat(filters.size()).isEqualTo(1);
    }

    @Test
    public void testFilterWithTwoFiltersFilterExtraction() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        requestParams.put("directors", "John Botti");
        requestParams.put("producers", "John Botti");
        Map<String, String[]> filters = extractFilters(requestParams);
        assertThat(filters).isNotEmpty();
        assertThat(filters.size()).isEqualTo(2);
    }

    @Test
    public void testCommaSeparatedFilters() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("q", "terminator");
        requestParams.put("directors", "John Botti,Erik Fleming");
        requestParams.put("producers", "John Botti");
        Map<String, String[]> filters = extractFilters(requestParams);

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
                        //return super.matches(value);
                        return value.length == 2;
                    }
                });

        buildQuery("terminator", filters);
    }

    public static SearchRequestBuilder movieSearchRequestBuilder(Client client) {
        return client.prepareSearch("boole").setTypes("movies");
    }

    public void buildQuery(String searchRequest, Map<String, String[]> filters) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchRequest);

        SearchRequestBuilder builder = movieSearchRequestBuilder(client);//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");

        BoolFilterBuilder filterBuilder = FilterBuilders.boolFilter();

        filters.entrySet().forEach(entry -> {
            String key = entry.getKey();
            String[] values = entry.getValue();
            BoolQueryBuilder mQueryBuilder = QueryBuilders.boolQuery();
            for (int index = 0; index < values.length; index++) {
                mQueryBuilder.should(QueryBuilders.matchQuery(key + ".name.untouched", values[index]));
            }
            filterBuilder.should(FilterBuilders.nestedFilter(entry.getKey(), mQueryBuilder));
           // System.out.println(boolFilterBuilder.toString());
        });

        //QueryBuilders.
        FilteredQueryBuilder filteredQueryBuilder = QueryBuilders.filteredQuery(queryBuilder, filterBuilder);
        builder.setQuery(filteredQueryBuilder);
        //builder.
       /* builder.setFrom(pageable.getPageNumber())
                .setSize(pageable.getPageSize());*/

        //addAggregations(builder);


        logger.debug("Request Sample: {}", builder.toString());

        //return builder.execute().actionGet();
    }


    @Test
    public void testSearch() throws IOException {
        SearchResponse sr = searchService.search("terminator", new HashMap<>(), new PageRequest(0, 25));
        assertThat(sr).isNotNull();
        assertThat(sr.getHits()).isNotNull();
        assertThat(sr.getHits().getHits()).isNotNull().isNotEmpty();
    }
}
