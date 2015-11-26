package com.boole.index.listener;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class ElapsedTimeJobListener implements JobExecutionListener {

    private final static Logger logger = LoggerFactory.getLogger(ElapsedTimeJobListener.class);

    private StopWatch stopWatch = new StopWatch();

    @BeforeJob
    public void beforeJob(JobExecution jobExecution) {
        logger.debug("Called beforeJob().");

        stopWatch.start();
    }

    @AfterJob
    public void afterJob(JobExecution jobExecution) {
        logger.debug("Called afterJob().");

        stopWatch.stop();
        logger.info("=============== Elapsed Time ========================");
        logger.info("Elapsed time : " + DurationFormatUtils.formatDurationHMS(stopWatch.getTotalTimeMillis()));
        logger.info("=============== Elapsed Time ========================");
    }
}