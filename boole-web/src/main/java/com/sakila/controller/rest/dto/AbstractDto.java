package com.sakila.controller.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created on 10/22/2015.
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class AbstractDto implements Serializable {

    private DateTime lastUpdated;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(DateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
