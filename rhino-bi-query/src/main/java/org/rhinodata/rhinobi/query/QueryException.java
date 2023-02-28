package org.rhinodata.rhinobi.query;

import org.rhinodata.rhinobi.common.api.RbException;

/**
 * @author chenye
 * @date 2023-02-27
 */
public class QueryException extends RbException {
  public QueryException(String message) {
    super(message);
  }

  public QueryException(Throwable cause) {
    super(cause);
  }

  public QueryException(String message, Throwable cause) {
    super(message, cause);
  }
}
