package com.sakila.service;

import com.sakila.index.domain.IndexFilm;
import com.sakila.repository.FilmRepository;
import com.sakila.util.exceptions.NonIndexableItemException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.Bulk;
import io.searchbox.core.BulkResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 11/3/2015.
 */
@Service("filmIndexer")
public class FilmIndexerImpl implements Indexer<IndexFilm> {

    private final FilmRepository filmRepository;
    private final JestClient jestClient;

    @Autowired
    public FilmIndexerImpl(FilmRepository filmRepository, JestClient jestClient) {
        this.filmRepository = filmRepository;
        this.jestClient = jestClient;
    }

    @Override
    public Iterable<IndexFilm> indexableItems() {
        return filmRepository.findAllWithDetails()
                .stream()
                .map(IndexFilm::new)
                .collect(Collectors.toSet());
    }

    @Override
    public void bulkIndexItems(List<IndexFilm> films) {
        try {
            boolean indexExists = jestClient.execute(new IndicesExists.Builder("sakila").build()).isSucceeded();
            if (!indexExists) {
                jestClient.execute(new CreateIndex.Builder("sakila").build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bulk.Builder bulkBuilder = new Bulk.Builder();
        films.forEach(indexFilm ->
                bulkBuilder.addAction(new Index.Builder(indexFilm)
                        .index("sakila")
                        .type("film")
                        .id(String.valueOf(indexFilm.getId()))
                        .build()));
        Bulk bulk = bulkBuilder.build();


       /* jestClient.executeAsync(bulk, new JestResultHandler<BulkResult>() {
            @Override
            public void completed(BulkResult result) {
                System.out.println("completed==>> successfully;");
            }

            @Override
            public void failed(Exception ex) {
                System.out.println(ex.toString());
            }
        });*/
        try {
            BulkResult result = jestClient.execute(bulk);
            System.out.println("Blocking Indexing: " + result.isSucceeded());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void indexItem(IndexFilm indexable) {
        if (indexable == null)
            throw new NonIndexableItemException("The Item cannot be indexed");

        try {
            boolean indexExists = jestClient.execute(new IndicesExists.Builder("sakila").build()).isSucceeded();
            if (!indexExists) {
                jestClient.execute(new CreateIndex.Builder("sakila").build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //now lets index the sucker
        Index index = new Index.Builder(indexable).index("sakila").type("film").id(String.valueOf(indexable.getId())).build();
        jestClient.executeAsync(index, new JestResultHandler<DocumentResult>() {
            @Override
            public void completed(DocumentResult result) {
                System.out.println("completed==>>;" + result.getId());
            }

            @Override
            public void failed(Exception ex) {
                System.out.println(ex.toString());
            }
        });
    }

    @Override
    public String counterName() {
        return "films";
    }

    @Override
    public String getId(IndexFilm indexable) {
        return String.valueOf(indexable.getId());
    }
}
