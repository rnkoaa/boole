package com.boole.web.controller.rest;

import io.searchbox.client.JestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/5/15.
 */
@RestController
@RequestMapping(value = "api/search")
public class ElasticIndexController {

    private static final Logger logger = LoggerFactory.getLogger(ElasticIndexController.class);

    /*@Autowired
    JestClient jestClient;*/

    /*//store with id
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
    public String search(@RequestBody BooleSearchRequest BooleSearchRequest) {
        String searchTerm = BooleSearchRequest.getSearchTerm().toLowerCase();

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
                .addIndex("boole")
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
    }*/


}
