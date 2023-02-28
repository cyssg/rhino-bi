package org.rhinodata.rhinobi.query;

import org.rhinodata.rhinobi.common.chain.Handler;
import org.rhinodata.rhinobi.common.chain.Pipeline;
import org.rhinodata.rhinobi.query.analysis.QueryAnalyzer;
import org.rhinodata.rhinobi.query.analysis.Statement;
import org.rhinodata.rhinobi.query.common.QueryResult;
import org.rhinodata.rhinobi.query.dsl.Query;
import org.rhinodata.rhinobi.query.pipeline.QueryPipeline;
import org.rhinodata.rhinobi.query.plan.PlanNode;
import org.rhinodata.rhinobi.query.plan.QueryPlanner;
import org.rhinodata.rhinobi.query.runner.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-04
 */
@Service
public class QueryServiceImpl implements QueryService {

  private final QueryBeans queryBeans;

  @Autowired
  public QueryServiceImpl(QueryBeans queryBeans) {
    this.queryBeans = queryBeans;
  }

  @Override
  public QueryResult query(Query query) {
    QueryContext queryContext = new QueryContext(query.getQueryId(), queryBeans);
    Statement statement = new QueryAnalyzer(queryContext).analyze(query);
    PlanNode planNode = new QueryPlanner(queryContext).plan(statement);
    QueryRunner.getInstance(planNode).exec(queryContext);
    return queryContext.getQueryResult();
  }

  QueryPipeline defaultQueryPipeline(QueryContext queryContext) {
    return new QueryPipeline() {
      private List<Handler> queryHandlers = new ArrayList<>();

      @Override
      public void exec() {
        queryHandlers.forEach(
            handler -> {
              handler.doHandler(queryContext);
            });
      }

      @Override
      public Pipeline addHandler(Handler handler) {
        queryHandlers.add(handler);
        return this;
      }
    };
  }
}
