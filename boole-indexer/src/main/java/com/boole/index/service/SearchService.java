package com.boole.index.service;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.domain.Pageable;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/27/15.
 */
public interface SearchService {

    /**
     * this is a search using the match_all query with the requested string.
     *
     * @param searchRequest
     * @return
     */
    SearchResponse search(String searchRequest);

    SearchResponse search(String searchRequest, Pageable pageable);
}
