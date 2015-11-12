package com.boole.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

/**
 * Created on 11/3/2015.
 */
@Service
public class IndexService {
    private final ExecutorService executorService;
    private final CounterService countersService;

    private static final Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    public IndexService(ExecutorService executorService, CounterService countersService) {
        this.executorService = executorService;
        this.countersService = countersService;
    }

    public <T> void index(final Indexer<T> indexer) {
        logger.debug("Indexing " + indexer.counterName());
        for (final T indexable : indexer.indexableItems()) {
            executorService.submit(() -> {
                try {
                    indexer.indexItem(indexable);
                    countersService.increment("search.indexes." + indexer.counterName() + ".processed");
                } catch (Exception e) {
                    String message =
                            String.format("Unable to index an entry of '%s' with id: '%s' -> (%s, %s)", indexer
                                    .counterName(), indexer.getId(indexable), e.getClass().getName(), e
                                    .getMessage());

                    logger.warn(message);
                    countersService.increment("search.indexes." + indexer.counterName() + ".errors.count");
                }
            });
        }
        countersService.increment("search.indexes." + indexer.counterName() + ".refresh.count");
    }
}
