package com.boole.web.controller.rest.components;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/12/15.
 */
public class RestErrorResponse {
    private int errorCode;
    private String message;

    public RestErrorResponse() {
    }

    public RestErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}