package com.boole.index.config;

import com.boole.common.BooleCommonApplicationConfig;
import com.boole.common.domain.Movie;
import com.boole.common.domain.dto.MovieDTO;
import com.boole.index.listener.ElapsedTimeJobListener;
import com.boole.index.listener.MovieChunkListener;
import com.boole.index.processor.MovieItemProcessor;
import com.boole.index.reader.MovieItemReader;
import com.boole.index.writer.MovieItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Configuration
@EnableBatchProcessing
@Import(BooleCommonApplicationConfig.class)
public class BatchConfig {

    @Bean
    public Job importMovieJob(JobBuilderFactory jobs, Step s1, ElapsedTimeJobListener listener) {
        return jobs.get("importMovieJob")
                //.incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, MovieItemReader movieItemReader,
                      MovieItemWriter movieItemWriter, MovieItemProcessor movieItemProcessor,
                      MovieChunkListener chunkListener) {
        return stepBuilderFactory.get("step1")
                .<Movie, MovieDTO>chunk(200)
                .reader(movieItemReader)
                .processor(movieItemProcessor)
                .writer(movieItemWriter)
                .listener(chunkListener)
                .allowStartIfComplete(true)
                        //.listener(elapsedTimeJobListener)
                .build();
    }
}
