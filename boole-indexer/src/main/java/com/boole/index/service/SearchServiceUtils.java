package com.boole.index.service;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/30/15.
 */
public class SearchServiceUtils {

    private SearchServiceUtils() {
    }

    public static final List<String> KNOWN_FILTERS = new ArrayList<>(5);

    static {
        KNOWN_FILTERS.add("directors");
        KNOWN_FILTERS.add("writers");
        KNOWN_FILTERS.add("producers");
        KNOWN_FILTERS.add("genres");
        KNOWN_FILTERS.add("actors");
    }

    public static final SearchServiceUtils searchServiceUtils = new SearchServiceUtils();


    private static String buildScriptStr(String bucketName) {
        return "doc[\'" + bucketName + ".id\'].value + \'|\' +" +
                " doc[\'" + bucketName + ".name.untouched\'].value";
    }

    private static void buildAggregations(XContentBuilder builder, boolean scripted) throws IOException {
        builder
                .startObject("aggs");

        KNOWN_FILTERS.forEach(knownFilter -> {
            try {
                buildBucket(knownFilter, builder, scripted);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        /*buildBucket("writers", builder, scripted);
        buildBucket("producers", builder, scripted);
        buildBucket("genres", builder, scripted);
        buildBucket("actors", builder, scripted);*/

        builder.endObject();
    }

    private static void buildBucket(String bucketName, XContentBuilder builder, boolean scripted) throws IOException {
        builder.startObject(bucketName)
                .startObject("nested")
                .field("path", bucketName)
                .endObject()
                .startObject("aggs")
                .startObject(bucketName + "_by_name_and_id")
                .startObject("terms");

        if (scripted) {
            builder.field("script", buildScriptStr(bucketName));
        } else
            builder.field("field", bucketName + ".name.untouched");

        builder.endObject()
                .endObject()
                .endObject()
                .endObject(); //end bucket
    }

    private static void buildQuery(String queryItem, XContentBuilder builder) throws IOException {
        builder.startObject("query")
                .startObject("filtered")
                .startObject("query")
                .startObject("match").field("_all", queryItem)
                .endObject()
                .endObject()
                .endObject()
                .endObject();
    }

    public XContentBuilder buildQueryWithAggs(String queryItem, boolean scriptedAggregations) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        //create the query object
        buildQuery(queryItem, builder);

        //create the aggregations object
        buildAggregations(builder, scriptedAggregations);
        builder.endObject();
        return builder;
    }

    public Map<String, String[]> extractFilters(Map<String, String> requestParams) {
        Map<String, String[]> results = new HashMap<>();
        requestParams.entrySet()
                .forEach(entry -> {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    if (KNOWN_FILTERS.contains(key)) {
                        String[] filters = value.split(",");
                        results.put(key, filters);
                    }
                });
        return results;
    }

    public static SearchServiceUtils getInstance() {
        return searchServiceUtils;
    }
}
