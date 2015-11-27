package com.boole.web.controller.rest.components;

import org.springframework.data.domain.Page;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 10/25/15.
 */

public class ResponseMetadata<T> {
    private long size;
    private long totalElements;
    private long totalPages;
    private int page;
    private String requestTimeTotal;

    public ResponseMetadata() {
        this(0, 0, 0, 0);
    }

    public ResponseMetadata(long size, long totalElements, long totalPages, int page) {
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.page = page;
    }

    public ResponseMetadata(Page<T> actorPage) {
        this(actorPage.getSize(), actorPage.getTotalElements(),
                actorPage.getTotalPages(), actorPage.getNumber());
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPage() {
        page += 1; //convert to 1-index paging
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getRequestTimeTotal() {
        return requestTimeTotal;
    }

    public void setRequestTimeTotal(String requestTimeTotal) {
        this.requestTimeTotal = requestTimeTotal;
    }
}