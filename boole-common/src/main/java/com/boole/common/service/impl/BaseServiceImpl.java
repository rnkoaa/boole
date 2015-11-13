package com.boole.common.service.impl;

import com.boole.common.service.BaseService;
import com.boole.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * Created on 10/21/2015.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

   /* private final GenericRepository genericRepository;
    private final Class<T> tClass;

    public BaseServiceImpl(GenericRepository genericRepository, Class<T> tClass){
        this.genericRepository = genericRepository;
        this.tClass = tClass;
    }*/


    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
    public static final String PER_PAGE = "per_page";
    public static final String PAGE_KEY = "page";
    private static final String SORT = "sort";

    protected Pageable createPageable(Map<String, String> requestParams, String defaultSortField) {
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

    protected Pageable createPageable(int page, int itemsPerPage, String defaultSortField) {
        if (itemsPerPage <= 0)
            itemsPerPage = 20;

        if (page <= 0)
            page = 0;
        else if (page > 0)
            page--;

        return (StringUtil.isNullOrEmpty(defaultSortField)) ?
                new PageRequest(page, itemsPerPage) :
                new PageRequest(page, itemsPerPage, new Sort(Sort.Direction.ASC, defaultSortField));

    }

}
