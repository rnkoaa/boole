package com.boole.index.tests;

import com.boole.index.config.IndexerTestConfig;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
public class IndexTest extends IndexerTestConfig {

    @Autowired
    Client client;

    @Test
    public void testClientConnection() {
        assertThat(client).isNotNull();
        /*SearchResponse searchResponse = client.prepareSearch("boole").get();
        long totalHits = searchResponse.getHits().totalHits();
        System.out.println(totalHits);*/

        SearchRequest request =
                Requests.searchRequest("boole")
                        .types("movies")
                        .source("{\"query\":{\"match\":{\"_all\":\"hans\"}}}");

        SearchResponse response = client.search(request).actionGet();
        System.out.println(response.getHits().totalHits());

        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("_all", "hans");
        queryBuilder.buildAsBytes();


        SearchRequestBuilder builder = client.prepareSearch("boole").setTypes("movies");//.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName","states");
        builder.setQuery(queryBuilder);
        SearchResponse searchResponse = builder.execute().actionGet();
        System.out.println(searchResponse.getHits().getTotalHits());

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            String source = searchHit.getSourceAsString();
            System.out.println(source);
        }
    }
}
