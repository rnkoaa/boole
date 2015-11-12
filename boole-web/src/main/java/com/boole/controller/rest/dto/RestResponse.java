package com.boole.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.boole.domain.Actor;
import org.springframework.data.domain.Page;

/**
 * Created on 10/22/2015.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RestResponse<T> {
    private T data;

    private ResponseMetadata responseMetadata;

    /**
     * Unused constructor for json annotaion processing
     */
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
        this.responseMetadata = new ResponseMetadata((int) total, start, totalPages, 0);
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
