package org.rhinodata.rhinobi.query.executor;

import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;
import org.rhinodata.rhinobi.query.common.QueryData;
import org.rhinodata.rhinobi.query.plan.SqlPlanNode;

/**
 * @author chenye
 * @date 2023-02-27
 */
public class QueryExecutorFactory {

  public static  QueryExecutor<QueryData> createQueryExecutor(QueryDataSource queryDataSource, SqlPlanNode sqlPlanNode) {
    switch(queryDataSource.getType()){
      case JDBC -> {
        return new JdbcQueryExecutor(queryDataSource,sqlPlanNode);
      }
    }
    return null;
  }
}
