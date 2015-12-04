package com.boole.web.controller.rest.components;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 12/2/15.
 */
public class AggregationBucket {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getDocCount() {
        return docCount;
    }

    public void setDocCount(long docCount) {
        this.docCount = docCount;
    }

    private long docCount;

    public AggregationBucket(){}

    public AggregationBucket(String key, long docCount){
        this.key = key;
        this.docCount = docCount;
    }
}
