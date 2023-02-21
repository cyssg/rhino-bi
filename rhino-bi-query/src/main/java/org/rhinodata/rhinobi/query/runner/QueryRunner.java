package org.rhinodata.rhinobi.query.runner;

import org.rhinodata.rhinobi.query.QueryContext;

/**
 * @author chenye
 * @date 2023-02-20
 */
public interface QueryRunner {

    void exec(QueryContext queryContext);
}
