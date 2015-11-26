package com.boole.index.reader;

import com.boole.common.domain.Movie;
import com.boole.common.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class MovieItemReader implements ItemReader<Movie> {

    private final MovieService movieService;
    private long totalMovieCount;
    private long count;
    private static final Logger logger = LoggerFactory.getLogger(MovieItemReader.class);

    @Autowired
    public MovieItemReader(MovieService movieService) {
        this.movieService = movieService;
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        totalMovieCount = movieService.findTotalMovieCount();
        //totalMovieCount = 300;
        logger.debug("There are {} movies to be read", totalMovieCount);
    }

    @AfterStep
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.debug("Called afterStep().");
        return null;
    }

    @Override
    public Movie read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < totalMovieCount) {
            long movieId = ++count;
            logger.debug("Select Movie With: {}", movieId);
            Optional<Movie> movieOptional = movieService.findWithFullDetails(movieId);
            return movieOptional.orElse(null);
        } else {
            return null;
        }
    }
}
