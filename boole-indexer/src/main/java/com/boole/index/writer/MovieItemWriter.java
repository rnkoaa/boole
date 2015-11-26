package com.boole.index.writer;

import com.boole.common.domain.dto.MovieDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class MovieItemWriter implements ItemWriter<MovieDTO> {

    private final Client client;
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(MovieItemWriter.class);

    @Autowired
    public MovieItemWriter(Client client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @Override
    public void write(List<? extends MovieDTO> items) throws Exception {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        bulkRequest.setRefresh(false);
        items.stream()
                .forEach(movieDTO -> {
                    try {
                        IndexRequestBuilder indexRequestBuilder =
                                client.prepareIndex("boole", "movies", String.valueOf(movieDTO.getId()))
                                        .setSource(objectMapper.writeValueAsBytes(movieDTO));
                        bulkRequest.add(indexRequestBuilder);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            logger.info("Failed to send all requests in bulk " + bulkResponse.buildFailureMessage());
        } else {
            logger.info("Elasticsearch Index updated in {} ms.", bulkResponse.getTookInMillis());
        }
    }
}
