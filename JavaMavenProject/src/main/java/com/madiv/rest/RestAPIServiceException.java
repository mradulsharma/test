package com.madiv.rest;

/**
 * @author Maddy Sharma
 */
public class RestAPIServiceException extends Exception {

    public RestAPIServiceException() {
        super();
    }

    public RestAPIServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestAPIServiceException(String message) {
        super(message);
    }

    public RestAPIServiceException(Throwable cause) {
        super(cause);
    }

}
