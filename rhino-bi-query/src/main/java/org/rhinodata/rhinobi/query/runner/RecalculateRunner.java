package org.rhinodata.rhinobi.query.runner;

import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.plan.RecalculatePlanNode;

/**
 * @author chenye
 * @date 2023-02-27
 */
public class RecalculateRunner implements QueryRunner{

    private final RecalculatePlanNode recalculatePlanNode;

    public RecalculateRunner(RecalculatePlanNode recalculatePlanNode) {
        this.recalculatePlanNode = recalculatePlanNode;
    }

    @Override
    public void exec(QueryContext queryContext) {

    }
}
