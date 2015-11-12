package com.sakila.controller.rest;

import com.sakila.controller.rest.dto.RestResponse;
import com.sakila.controller.rest.dto.SakilaSearchRequest;
import com.sakila.util.exceptions.SearchRequestFailedException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
@RestController
@RequestMapping(value = "api/search")
public class ElasticIndexController {

    private static final Logger logger = LoggerFactory.getLogger(ElasticIndexController.class);

    @Autowired
    JestClient jestClient;

    //store with id
    //options, include customers,inventories,staffs,manager,address
    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<Boolean> findStoreById(@PathVariable("id") Integer id) {
        return new RestResponse<>(true);
    }

    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String search(@RequestBody SakilaSearchRequest sakilaSearchRequest) {
        String searchTerm = sakilaSearchRequest.getSearchTerm().toLowerCase();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
                .queryStringQuery(searchTerm)
                .field("title", 20)
                .field("specialFeatures")
                .field("rating")
                .field("description")
                .field("categories.name")
                .field("actors.lastName", 5)
                .field("actors.firstName", 10)
                .field("originalLanguage.name")
                .analyzer("english"));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("sakila")
                .addType("film")
                .build();

        JestResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultJsonAsString = null;
        if (result != null) {
            resultJsonAsString = result.getJsonString();
        } else {
            throw new SearchRequestFailedException("Exception thrown while searching for " + searchTerm);
        }
        return resultJsonAsString;
    }


}
