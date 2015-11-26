package com.boole.index.processor;

import com.boole.common.domain.Movie;
import com.boole.common.domain.dto.MovieDTO;
import com.boole.common.service.mapper.MovieMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class MovieItemProcessor implements ItemProcessor<Movie, MovieDTO> {

    private static final Logger logger = LoggerFactory.getLogger(MovieItemProcessor.class);
    private final MovieMapperService movieMapperService;

    @Autowired
    public MovieItemProcessor(MovieMapperService movieMapperService) {
        this.movieMapperService = movieMapperService;
    }

    @Override
    public MovieDTO process(Movie item) throws Exception {

        //String dtoString = objectMapper.writeValueAsString(movieDTO);

        //logger.debug("dto json: {}", dtoString);
        return movieMapperService.mapMovie(item, "details");
    }
}
