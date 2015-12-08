package com.boole.web.controller.rest.components;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 12/5/15.
 */
public class TermsAggregationSerializer extends JsonSerializer<Map<String, AggregationBucket>> {
    @Override
    public void serialize(Map<String, AggregationBucket> value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartArray();
        for (Map.Entry<String, AggregationBucket> entry : value.entrySet()) {
            gen.writeStartObject();

            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
