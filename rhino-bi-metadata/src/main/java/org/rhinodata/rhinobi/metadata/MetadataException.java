package org.rhinodata.rhinobi.metadata;

import org.rhinodata.rhinobi.common.api.RbException;

/**
 * @author chenye
 * @date 2023-02-28
 */
public class MetadataException extends RbException {
    public MetadataException(String message) {
        super(message);
    }

    public MetadataException(Throwable cause) {
        super(cause);
    }

    public MetadataException(String message, Throwable cause) {
        super(message, cause);
    }
}
