package com.boole.index.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class MovieChunkListener implements ChunkListener {
    private static final Logger logger = LoggerFactory.getLogger(MovieChunkListener.class);

    @Override
    public void beforeChunk(ChunkContext context) {

    }

    @Override
    public void afterChunk(ChunkContext context) {
        logger.debug("Chunk Committed. Sleeping for 5s");
       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        logger.debug("Sleep completed. Now Moving to next chunk");
    }

    @Override
    public void afterChunkError(ChunkContext context) {

    }
}
