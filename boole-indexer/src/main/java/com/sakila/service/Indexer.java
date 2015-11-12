package com.sakila.service;

import com.sakila.index.domain.IndexFilm;

import java.util.List;

/**
 * Created on 11/3/2015.
 */
public interface Indexer<T> {
    Iterable<T> indexableItems();

    void bulkIndexItems(List<IndexFilm> films);

    void indexItem(T indexable);

    String counterName();

    String getId(T indexable);
}
