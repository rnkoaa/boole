package com.boole.web.controller.rest.components;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/12/15.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RestResponse<T> {
    private T data;

    private ResponseMetadata responseMetadata;

    public RestResponse() {
    }

    public RestResponse(T data) {
        this(data, null);
    }

    public RestResponse(T data, ResponseMetadata responseMetadata) {
        setData(data);
        this.responseMetadata = responseMetadata;
    }

    public RestResponse(T data, long total, int start, int totalPages, String sort, String order) {
        setData(data);
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResponseMetadata(ResponseMetadata responseMetadata) {
        this.responseMetadata = responseMetadata;
    }

    @JsonProperty("meta")
    public ResponseMetadata getResponseMetadata() {
        return responseMetadata;
    }
}


