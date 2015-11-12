package com.boole.controller.rest;

import com.boole.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/25/15.
 */
@RestController
public class AbstractRestController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractRestController.class);
    public static final String PER_PAGE = "per_page";
    public static final String PAGE_KEY = "page";
    private static final String SORT = "sort";

    protected Pageable createPageable(@RequestParam Map<String, String> requestParams, String defaultSortField) {
        int page = 0;
        if (requestParams.containsKey(PAGE_KEY)) {
            page = Integer.parseInt(requestParams.get(PAGE_KEY));
        }

        int perPage = 20;
        if (requestParams.containsKey(PER_PAGE))
            perPage = Integer.parseInt(requestParams.get(PER_PAGE));

        String sortField = defaultSortField;
        if (requestParams.containsKey(SORT))
            sortField = requestParams.get(SORT);
        logger.debug("Page Details: Page: {}, Per_Page: {}, SortField: {}", page, perPage, sortField);

        //paging is 0th based
        if (page > 0)
            page--;

        return (StringUtil.isNullOrEmpty(sortField)) ?
                new PageRequest(page, perPage) :
                new PageRequest(page, perPage, new Sort(Sort.Direction.ASC, sortField));
    }
}
