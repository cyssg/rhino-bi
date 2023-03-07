package org.rhinodata.rhinobi.query.request;

import lombok.Data;

/**
 * @author chenye
 * @date 2023-03-02
 */
@Data
public class QueryRequest {
  private String queryId;
  private String dql;
}
