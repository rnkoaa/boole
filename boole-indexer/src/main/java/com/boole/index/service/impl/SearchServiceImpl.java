package com.boole.index.service.impl;

import com.boole.index.service.SearchService;
import com.boole.index.service.SearchServiceUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.boole.index.service.SearchServiceUtils.KNOWN_FILTERS;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/27/15.
 */
@Component("searchService")
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    private final Client client;

    SearchServiceUtils searchServiceUtils = SearchServiceUtils.getInstance();

    @Autowired
    public SearchServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public SearchResponse search(String searchRequest) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchRequest);

        SearchRequestBuilder builder = movieSearchRequestBuilder(client);//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");
        builder.setQuery(queryBuilder);


        return builder.execute().actionGet();
    }

    @Override
    public SearchResponse search(String searchRequest, Map<String, String> requestParams, Pageable pageable) {

        Map<String, String[]> filters = searchServiceUtils.extractFilters(requestParams);

        SearchRequestBuilder builder = buildQuery(searchRequest, filters);
        builder.setFrom(pageable.getPageNumber())
                .setSize(pageable.getPageSize());

        addAggregations(builder);


        logger.debug("Request Sample: {}", builder.toString());

        return builder.execute().actionGet();
    }

    public SearchRequestBuilder buildQuery(String searchRequest, Map<String, String[]> filters) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchRequest);

        SearchRequestBuilder builder = movieSearchRequestBuilder(client);//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");

        BoolFilterBuilder filterBuilder = FilterBuilders.boolFilter();

        filters.entrySet()
                .forEach(entry -> {
                    String key = entry.getKey();
                    String[] values = entry.getValue();
                    BoolQueryBuilder mQueryBuilder = QueryBuilders.boolQuery();
                    for (String value : values) {
                        mQueryBuilder.should(QueryBuilders.matchQuery(key + ".name.untouched", value));
                    }
                    filterBuilder.should(FilterBuilders.nestedFilter(entry.getKey(), mQueryBuilder));
                });

        FilteredQueryBuilder filteredQueryBuilder = QueryBuilders.filteredQuery(queryBuilder, filterBuilder);
        builder.setQuery(filteredQueryBuilder);

        return builder;
    }

    private void addAggregations(SearchRequestBuilder builder) {
        KNOWN_FILTERS.forEach(aggField -> {
            AggregationBuilder aggregationBuilder = AggregationBuilders
                    .nested(aggField + "_aggs")
                    .path(aggField);
            aggregationBuilder.subAggregation(AggregationBuilders
                    .terms(aggField + "_by_name_and_id")
                    .script("doc[\'" + aggField + ".id\'].value + \'|\' + doc[\'" + aggField + ".name.untouched\'].value"));

            builder.addAggregation(aggregationBuilder);
        });

    }

    public static SearchRequestBuilder movieSearchRequestBuilder(Client client) {
        return client.prepareSearch("boole").setTypes("movies");
    }
}
