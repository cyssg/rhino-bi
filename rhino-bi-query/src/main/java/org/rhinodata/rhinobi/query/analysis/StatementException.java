package org.rhinodata.rhinobi.query.analysis;

import org.rhinodata.rhinobi.common.api.RbException;

/**
 * @author chenye
 * @date 2023-02-25
 */
public class StatementException extends RbException {

    public StatementException(String message) {
        super(message);
    }

    public StatementException(Throwable cause) {
        super(cause);
    }

    public StatementException(String message, Throwable cause) {
        super(message, cause);
    }
}
