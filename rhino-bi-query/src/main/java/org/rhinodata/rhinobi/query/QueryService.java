package org.rhinodata.rhinobi.query;

import org.rhinodata.rhinobi.query.common.QueryResult;
import org.rhinodata.rhinobi.query.dsl.Query;

/**
 * @author chenye
 * @date 2023-02-03
 */
public interface QueryService {

    /**
     * 查询
     * @param query
     * @return
     */
    QueryResult query(Query query);
}
