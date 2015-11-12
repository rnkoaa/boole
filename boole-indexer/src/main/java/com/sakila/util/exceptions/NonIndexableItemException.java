package com.boole.common.util.exceptions;

/**
 * Created on 11/5/2015.
 */
public class NonIndexableItemException extends RuntimeException {
    public NonIndexableItemException(String message) {
        super(message);
    }

    public NonIndexableItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonIndexableItemException(Throwable cause) {
        super(cause);
    }

    public NonIndexableItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
