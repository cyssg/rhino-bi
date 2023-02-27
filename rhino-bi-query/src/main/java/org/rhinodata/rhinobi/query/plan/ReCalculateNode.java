package org.rhinodata.rhinobi.query.plan;

/**
 * 需要再计算的Node
 * @author chenye
 * @date 2023-02-24
 */
public class ReCalculateNode extends PlanNode{
    public ReCalculateNode(Projects projects) {
        super(projects);
    }
}
