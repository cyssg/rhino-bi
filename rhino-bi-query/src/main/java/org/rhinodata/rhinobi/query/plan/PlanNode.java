package org.rhinodata.rhinobi.query.plan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-06
 */

public abstract class PlanNode {

    public final List<PlanNode> children;

    public PlanNode() {
        this.children = new ArrayList<>();
    }

    public List<PlanNode> getChildren() {
        return children;
    }

    public void addChild(PlanNode planNode) {
        this.children.add(planNode);
    }

    public Projects getProjects() {
        for (PlanNode p : children) {
            if (p instanceof Projects) {
                return (Projects) p;
            }
        }
        return null;
    }

    public <R, C> R accept(PlanVisitor<R, C> visitor, C context) {
        return visitor.visitPlan(this,context);
    }
}
