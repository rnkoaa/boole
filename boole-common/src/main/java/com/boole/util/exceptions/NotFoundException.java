package com.sakila.util.exceptions;

/**
 * Created on 10/22/2015.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
