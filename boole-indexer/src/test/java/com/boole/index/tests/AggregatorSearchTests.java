package com.boole.index.tests;

import com.boole.index.config.IndexerTestConfig;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/29/15.
 */
public class AggregatorSearchTests extends IndexerTestConfig {

    @Autowired
    Client client;

    Logger logger = LoggerFactory.getLogger(AggregatorSearchTests.class);

    public SearchResponse doSearch(String searchRequest) throws IOException {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", searchRequest);


        SearchRequestBuilder builder = movieSearchRequestBuilder(client);//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");
        //builder.setQuery(queryBuilder);
        //builder.addAggregation()
        XContentBuilder xContentBuilder = buildQueryWithScriptedAggs("terminator");

        builder.setSource(xContentBuilder);
       /* builder.setFrom(pageable.getPageNumber())
                .setSize(pageable.getPageSize());*/

      /*  AggregationBuilder aggregationBuilder = AggregationBuilders
                .terms("director.name")
                .field("")*/

        logger.debug("Request Sample: {}", builder.toString());
        System.out.println("Checkout changes");

        return builder.execute().actionGet();
    }

    public static SearchRequestBuilder movieSearchRequestBuilder(Client client) {
        return client.prepareSearch("boole").setTypes("movies");
    }

    @Test
    public void testFind() throws IOException {

        SearchResponse searchResponse = doSearch("terminator");
        System.out.println(searchResponse.toString());
        assertThat(true).isTrue();
    }

    String buildScriptStr(String bucketName) {
        return "doc[\'" + bucketName + ".id\'].value + \'|\' +" +
                " doc[\'" + bucketName + ".name.untouched\'].value";
    }

    void buildAggregations(XContentBuilder builder) throws IOException {
        builder
                .startObject("aggs");

        buildBucket("directors", builder);
        buildBucket("writers", builder);
        buildBucket("producers", builder);
        buildBucket("genres", builder);
        buildBucket("actors", builder);

        builder.endObject();
    }

    void buildScriptedAggregations(XContentBuilder builder) throws IOException {
        builder
                .startObject("aggs");

        buildScriptedBucket("directors", builder);
        buildScriptedBucket("writers", builder);
        buildScriptedBucket("producers", builder);
        buildScriptedBucket("genres", builder);
        buildScriptedBucket("actors", builder);

        builder.endObject();
    }

    void buildBucket(String bucketName, XContentBuilder builder) throws IOException {
        builder.startObject(bucketName)
                .startObject("nested")
                .field("path", bucketName)
                .endObject()
                .startObject("aggs")
                .startObject(bucketName + "_by_name_and_id")
                .startObject("terms")
                .field("field", bucketName + ".name.untouched")
                .endObject()
                .endObject()
                .endObject()
                .endObject(); //end bucket
    }

    void buildScriptedBucket(String bucketName, XContentBuilder builder) throws IOException {
        builder.startObject(bucketName)
                .startObject("nested")
                .field("path", bucketName)
                .endObject()
                .startObject("aggs")
                .startObject(bucketName + "_by_name_and_id")
                .startObject("terms")
                .field("script", buildScriptStr(bucketName))
                .endObject()
                .endObject()
                .endObject()
                .endObject(); //end bucket
    }

    void buildQuery(String queryItem, XContentBuilder builder) throws IOException {
        builder.startObject("query")
                .startObject("filtered")
                .startObject("query")
                .startObject("match").field("_all", queryItem)
                .endObject()
                .endObject()
                .endObject()
                .endObject();
    }

    void buildQueryWithAggs(String queryItem) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        //create the query object
        buildQuery(queryItem, builder);

        //create the aggregations object
        buildAggregations(builder);
        builder.endObject();
        System.out.println(builder.string());
    }

    XContentBuilder buildQueryWithScriptedAggs(String queryItem) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        //create the query object
        buildQuery(queryItem, builder);

        //create the aggregations object
        buildScriptedAggregations(builder);
        builder.endObject();
        return builder;
    }
}
