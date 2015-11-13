package com.boole.index.service;


/**
 * Created on 11/3/2015.
 */
public interface Indexer<T> {
    Iterable<T> indexableItems();

    //void bulkIndexItems(List<IndexFilm> films);

    void indexItem(T indexable);

    String counterName();

    String getId(T indexable);
}
