package org.rhinodata.rhinobi.query.runner;

import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.common.QueryData;
import org.rhinodata.rhinobi.query.executor.QueryExecutor;
import org.rhinodata.rhinobi.query.executor.QueryExecutorFactory;
import org.rhinodata.rhinobi.query.plan.SqlPlanNode;

/**
 * @author chenye
 * @date 2023-02-20
 */
public class SimpleSqlRunner implements QueryRunner {

  private final SqlPlanNode sqlPlanNode;
  private QueryContext queryContext;

  public SimpleSqlRunner(SqlPlanNode sqlPlanNode) {
    this.sqlPlanNode = sqlPlanNode;
  }

  @Override
  public void exec(QueryContext queryContext) {
    this.queryContext = queryContext;
    QueryDataSource queryDataSource =
        queryContext.getQueryBeans().dataSourceService().getByName(sqlPlanNode.getDatasourceName());
    QueryExecutor<QueryData> queryExecutor =
        QueryExecutorFactory.createQueryExecutor(queryDataSource, sqlPlanNode);
    QueryData queryData = queryExecutor.execute();
  }
}
