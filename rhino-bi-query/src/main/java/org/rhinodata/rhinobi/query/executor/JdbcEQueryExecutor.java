package org.rhinodata.rhinobi.query.executor;

import org.rhinodata.rhinobi.dataset.domain.QueryDataSource;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.runner.QueryRunner;

/**
 * @author chenye
 * @date 2023-02-21
 */
public class JdbcEQueryExecutor implements QueryExecutor<Void>{

    private QueryDataSource queryDataSource;
    private QueryContext queryContext;

    @Override
    public Void execute() {
//        QueryRunner queryRunner = queryContext.getQueryRunner();
//        queryRunner.g
        return null;
    }
}
