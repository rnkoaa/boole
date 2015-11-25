package com.boole.index.processor;

import com.boole.common.domain.Movie;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class MovieItemProcessor implements ItemProcessor<Movie, Byte[]> {
    @Override
    public Byte[] process(Movie item) throws Exception {
        return new Byte[0];
    }
}
