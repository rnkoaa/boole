package com.boole.web.controller.rest.components;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 12/2/15.
 */
public class SearchRestResponse {

    private static final Logger logger = LoggerFactory.getLogger(SearchRestResponse.class);

    private final Map<String, List<AggregationBucket>> termsAggregations;
    private final SearchHit[] hits;

    public SearchRestResponse(SearchResponse searchResponse) {
        hits = searchResponse.getHits().getHits();
        Map<String, Aggregation> aggregations = searchResponse.getAggregations().asMap();
        termsAggregations = new HashMap<>();

        if (aggregations != null) {
            aggregations.forEach((key, value) -> {
                logger.info("Aggregation Key: {}", key);
                switch (key) {
                    case "directors_aggs":
                        Nested nestedAgg = searchResponse.getAggregations().get("directors_aggs");
                        Terms termsAggs = nestedAgg.getAggregations().get("directors_by_name_and_id");
                        //termsAggregations.put(key, new AggregationBucket(termsAggs.getBucketByKey("")));
                        List<AggregationBucket> aggregationBuckets = termsAggs.getBuckets().stream()
                                .map(bucket ->
                                        new AggregationBucket(bucket.getKey(), bucket.getDocCount()))
                                .collect(Collectors.toList());
                        termsAggregations.put(key, aggregationBuckets);
                        break;
                    case "writers_aggs":
                        nestedAgg = searchResponse.getAggregations().get("writers_aggs");
                        termsAggs = nestedAgg.getAggregations().get("writers_by_name_and_id");
                        aggregationBuckets = termsAggs.getBuckets().stream()
                                .map(bucket ->
                                        new AggregationBucket(bucket.getKey(), bucket.getDocCount()))
                                .collect(Collectors.toList());
                        termsAggregations.put(key, aggregationBuckets);
                        break;
                    case "actors_aggs":
                        nestedAgg = searchResponse.getAggregations().get("actors_aggs");
                        termsAggs = nestedAgg.getAggregations().get("actors_by_name_and_id");
                        aggregationBuckets = termsAggs.getBuckets().stream()
                                .map(bucket ->
                                        new AggregationBucket(bucket.getKey(), bucket.getDocCount()))
                                .collect(Collectors.toList());
                        termsAggregations.put(key, aggregationBuckets);
                        break;
                    case "producers_aggs":
                        nestedAgg = searchResponse.getAggregations().get("producers_aggs");
                        termsAggs = nestedAgg.getAggregations().get("producers_by_name_and_id");
                        aggregationBuckets = termsAggs.getBuckets().stream()
                                .map(bucket ->
                                        new AggregationBucket(bucket.getKey(), bucket.getDocCount()))
                                .collect(Collectors.toList());
                        termsAggregations.put(key, aggregationBuckets);
                        break;
                    case "genres_aggs":
                        nestedAgg = searchResponse.getAggregations().get("genres_aggs");
                        termsAggs = nestedAgg.getAggregations().get("genres_by_name_and_id");
                        aggregationBuckets = termsAggs.getBuckets().stream()
                                .map(bucket ->
                                        new AggregationBucket(bucket.getKey(), bucket.getDocCount()))
                                .collect(Collectors.toList());
                        termsAggregations.put(key, aggregationBuckets);
                        break;
                }
            });
        }
    }


    public Map<String, List<AggregationBucket>> getAggregations() {
        return termsAggregations;
    }

    public SearchHit[] getHits() {
        return hits;
    }
}
