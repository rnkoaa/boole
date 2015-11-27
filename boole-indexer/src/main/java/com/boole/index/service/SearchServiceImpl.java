package com.boole.index.service;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/27/15.
 */
@Component("searchService")
public class SearchServiceImpl implements SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);
    private final Client client;

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
    public SearchResponse search(String searchRequest, Pageable pageable) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchRequest);

        SearchRequestBuilder builder = movieSearchRequestBuilder(client);//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");
        builder.setQuery(queryBuilder);
        builder.setFrom(pageable.getPageNumber())
                .setSize(pageable.getPageSize());

        logger.debug("Request Sample: {}", builder.toString());
        System.out.println("Checkout changes");

        return builder.execute().actionGet();
    }

    public static SearchRequestBuilder movieSearchRequestBuilder(Client client) {
        return client.prepareSearch("boole").setTypes("movies");
    }
}
