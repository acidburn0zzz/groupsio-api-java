package com.github.lake54.groupsio.api.exception;

import com.github.lake54.groupsio.api.domain.Error;
import com.github.lake54.groupsio.api.domain.enums.error.ErrorType;

/**
 * Exception class to be thrown during errors encountered
 * during requests to the groups.io API.
 */
public class GroupsIOApiException extends Exception {

    /**
     * Creates an exception from an error instance.
     *
     * @param error
     *      the error instance to create from.
     */
    public GroupsIOApiException(Error error) {
        this(error.type());
    }

    /**
     * Creates an exception from an error type.
     *
     * @param errorType
     *      the error type to create from.
     */
    public GroupsIOApiException(ErrorType errorType) {
        this(errorType.toString());
    }

    /**
     * Creates an exception from a message string.
     *
     * @param message
     *      the message String to generate an error from.
     */
    public GroupsIOApiException(String message) {
        super(message);
    }
}
