package org.rhinodata.rhinobi.query;

import org.rhinodata.rhinobi.common.chain.Handler;
import org.rhinodata.rhinobi.common.chain.Pipeline;
import org.rhinodata.rhinobi.query.common.QueryResult;
import org.rhinodata.rhinobi.query.dsl.Query;
import org.rhinodata.rhinobi.query.pipeline.QueryPipeline;
import org.rhinodata.rhinobi.query.plan.PlanNode;
import org.rhinodata.rhinobi.query.plan.QueryAnalyzer;
import org.rhinodata.rhinobi.query.runner.SimpleSqlRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-04
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Resource
    private QueryBeans queryBeans;

    @Override
    public QueryResult query(Query query) {

        PlanNode planNode = new QueryAnalyzer(queryBeans).analyze(query);
        QueryContext queryContext = new QueryContext(query.getQueryId(), queryBeans);
        new SimpleSqlRunner(planNode).exec(queryContext);
//        defaultQueryPipeline(queryContext).exec();
        return queryContext.getQueryResult();
    }

    QueryPipeline defaultQueryPipeline(QueryContext queryContext) {
        return new QueryPipeline() {
            private List<Handler> queryHandlers = new ArrayList<>();

            @Override
            public void exec() {
                queryHandlers.forEach(handler -> {
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
