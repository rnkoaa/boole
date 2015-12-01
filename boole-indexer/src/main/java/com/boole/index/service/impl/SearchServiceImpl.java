package com.boole.index.service.impl;

import com.boole.index.service.SearchService;
import com.boole.index.service.SearchServiceUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

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
    public SearchResponse search(String searchRequest, Pageable pageable) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchRequest);

        SearchRequestBuilder builder = movieSearchRequestBuilder(client);//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");
        builder.setQuery(queryBuilder);
        builder.setFrom(pageable.getPageNumber())
                .setSize(pageable.getPageSize());

        addAggregations(builder);


        logger.debug("Request Sample: {}", builder.toString());
        SearchResponse searchResponse = builder.execute().actionGet();

        Nested nestedAgg = searchResponse.getAggregations().get("directors_aggs");
        // Collection<Nested> buckets = nested.getBuckets();
        Terms termsAggs = nestedAgg.getAggregations().get("directors_by_name_and_id");
        termsAggs.getBuckets().forEach(termsAgg ->
                System.out.println(termsAgg.getDocCount()));
        System.out.println(searchResponse.toString());
        return searchResponse;
    }

    private void addAggregations(SearchRequestBuilder builder) {
        List<String> aggFields = Arrays.asList("directors", "producers", "actors", "writers", "genres");
        aggFields.forEach(aggField -> {
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
