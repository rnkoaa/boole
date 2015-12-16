package com.boole.web.controller.rest;

import com.boole.index.service.SearchService;
import com.boole.web.controller.rest.components.ResponseMetadata;
import com.boole.web.controller.rest.components.RestResponse;
import com.boole.web.controller.rest.components.SearchRestResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/27/15.
 */
@RestController
@RequestMapping("/api/search")
public class SearchController extends AbstractRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static List<String> KNOWN_FILTERS = new ArrayList<>(5);

    static {
        KNOWN_FILTERS.add("directors");
        KNOWN_FILTERS.add("producers");
        KNOWN_FILTERS.add("writers");
        KNOWN_FILTERS.add("genres");
        KNOWN_FILTERS.add("actors");
    }

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public DeferredResult<RestResponse<SearchRestResponse>> simpleSearch(@RequestParam("q") final String searchTerm,
                                                                         @RequestParam Map<String, String> requestParams) {
        logger.info("Request received for Search Param: {}", searchTerm);
        //spit(requestParams);
        Map<String, String[]> filters = extractFilters(requestParams);
        final Pageable pageable = createPageable(requestParams, null);
        DeferredResult<RestResponse<SearchRestResponse>> deferredResult = new DeferredResult<>();
        CompletableFuture
                .supplyAsync(() ->
                        searchService.search(searchTerm, filters, pageable))
                .whenCompleteAsync((result, throwable) ->
                        deferredResult.setResult(createPage(pageable, result)));

        return deferredResult;
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

    private void spit(Map<String, String> requestParams) {
        System.out.println("Request Params --------------------------------------------");
        requestParams
                .forEach((key, value) -> {
                    logger.debug("Key: {}, Value: {}", key, value);
                });
    }

    private RestResponse<SearchRestResponse> createPage(Pageable pageable, SearchResponse result) {
        long totalResultsCount = result.getHits().getTotalHits();

        int pageSize = pageable.getPageSize();
        long totalPages = (long) Math.ceil(totalResultsCount / pageSize) - 1;
        if (totalPages < 1)
            totalPages = 1;
        ResponseMetadata responseMetadata = new ResponseMetadata(totalResultsCount,
                pageSize,
                totalPages,
                pageable.getPageNumber());
        responseMetadata.setRequestTimeTotal(result.getTookInMillis() + "ms");
        SearchRestResponse searchRestResponse = new SearchRestResponse(result);
        return new RestResponse<>(searchRestResponse, responseMetadata);
    }

}
