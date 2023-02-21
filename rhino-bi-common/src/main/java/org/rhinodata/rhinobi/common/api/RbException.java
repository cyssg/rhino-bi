package org.rhinodata.rhinobi.common.api;

/**
 * @author chenye
 * @date 2023-02-21
 */
public class RbException extends RuntimeException{

    /**
     * @param message the detail message.
     */
    public RbException(String message) {
        super(message);
    }

    /**
     * @param cause the cause.
     */
    public RbException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message the detail message.
     * @param cause   the cause.
     */
    public RbException(String message, Throwable cause) {
        super(message, cause);
    }
}
