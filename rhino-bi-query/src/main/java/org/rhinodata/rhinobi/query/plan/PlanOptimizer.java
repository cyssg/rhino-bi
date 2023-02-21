package org.rhinodata.rhinobi.query.plan;

import org.rhinodata.rhinobi.query.QueryBeans;

/**
 * @author chenye
 * @date 2023-02-17
 */
public record PlanOptimizer(QueryBeans queryBeans) {

    public PlanNode optimizer(PlanNode planNode) {
        return planNode;
    }
}
