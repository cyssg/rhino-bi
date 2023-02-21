package org.rhinodata.rhinobi.query;

import lombok.Getter;
import org.rhinodata.rhinobi.query.common.QueryResult;

/**
 * @author chenye
 * @date 2023-02-06
 */
@Getter
public class QueryContext {

    private final String queryId;

    private final QueryResult queryResult;

    private final QueryBeans queryBeans;


    public QueryContext(String queryId, QueryBeans queryBeans) {
        this.queryId = queryId;
        this.queryBeans = queryBeans;
        this.queryResult = new QueryResult();
    }
}
