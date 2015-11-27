package com.boole.web.controller.rest;

import com.boole.index.service.SearchService;
import com.boole.web.controller.rest.components.ResponseMetadata;
import com.boole.web.controller.rest.components.RestResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

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
    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public DeferredResult<RestResponse<SearchHit[]>> simpleSearch(@RequestParam("q") final String searchTerm,
                                                                  @RequestParam Map<String, String> requestParams) {
        logger.info("Request received for Search Param: {}", searchTerm);
        final Pageable pageable = createPageable(requestParams, null);
        DeferredResult<RestResponse<SearchHit[]>> deferredResult = new DeferredResult<>();
        CompletableFuture
                .supplyAsync(() -> searchService.search(searchTerm, pageable))
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(createPage(pageable, result)));

        return deferredResult;
    }

    private RestResponse<SearchHit[]> createPage(Pageable pageable, SearchResponse result) {
        long totalResultsCount = result.getHits().getTotalHits();
        int pageSize = pageable.getPageSize();
        long totalPages = (long) Math.ceil(totalResultsCount / pageSize) - 1;
        ResponseMetadata responseMetadata = new ResponseMetadata(totalResultsCount,
                pageSize,
                totalPages,
                pageable.getPageNumber());
        responseMetadata.setRequestTimeTotal(result.getTookInMillis() + "ms");
        return new RestResponse<>(result.getHits().getHits(), responseMetadata);
    }

}
