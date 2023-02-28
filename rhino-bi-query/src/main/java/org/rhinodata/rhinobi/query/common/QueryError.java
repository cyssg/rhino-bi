package org.rhinodata.rhinobi.query.common;

/**
 * @author chenye
 * @date 2023-02-27
 */
public record QueryError(String message, int errorCode, String errorName,
                         String errorType) {

    @Override
    public String toString() {
        return "QueryError{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", errorName='" + errorName + '\'' +
                ", errorType='" + errorType + '\'' +
                '}';
    }
}
