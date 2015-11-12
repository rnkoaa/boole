package com.sakila.util.exceptions;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/8/15.
 */
public class SearchRequestFailedException extends RuntimeException {
    public SearchRequestFailedException(String message) {
        super(message);
    }

    public SearchRequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchRequestFailedException(Throwable cause) {
        super(cause);
    }
}
