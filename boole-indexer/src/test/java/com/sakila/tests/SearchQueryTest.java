package com.boole.tests;

import com.google.gson.Gson;
import com.boole.config.IndexerTestConfig;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/7/15.
 */
public class SearchQueryTest extends IndexerTestConfig {
    @Autowired
    JestClient jestClient;

    @Autowired
    Gson gson;

    @Test
    public void testSearchQueryBuilder() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
                .queryStringQuery("boring")
                .field("title", 20)
                .field("specialFeatures")
                .field("description")
                .field("categories.name")
                .field("actors.lastName", 5)
                .field("actors.firstName", 10)
                .field("originalLanguage.name")
                .analyzer("english"));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("boole")
                .addType("film")
                .build();

        System.out.println(search.toString());
        assertThat(search).isNotNull();
        System.out.println(search.getData(gson));
        JestResult result = jestClient.execute(search);
        System.out.println(result.getJsonString());
        //assertThat(jestClient).isNotNull();
    }
}
