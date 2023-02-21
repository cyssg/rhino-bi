package org.rhinodata.rhinobi.query.plan;

/**
 * @author chenye
 * @date 2023-02-20
 */
public class PlanVisitor<R, C> {


    public R visitProjects(Projects projects, C context) {
        return null;
    }
    public R visitFrom(From from, C context) {
        return null;
    }
    public R visitPlan(PlanNode planNode, C context) {
        if (planNode instanceof Projects) {
            return visitProjects((Projects) planNode, context);
        }
        if (planNode instanceof From) {
            return visitFrom((From) planNode, context);
        }
        return null;
    }


}
