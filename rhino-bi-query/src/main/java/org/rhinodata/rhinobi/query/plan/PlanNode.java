package org.rhinodata.rhinobi.query.plan;

import lombok.Getter;
import lombok.ToString;
import org.rhinodata.rhinobi.query.common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-06
 */
@ToString
public abstract class PlanNode implements Node {

  /** children */
  private final List<PlanNode> children;

  /** 投影字段 */
  @Getter private final Projects projects;

  public PlanNode(Projects projects) {
    this.children = new ArrayList<>();
    this.projects = projects;
  }

  @Override
  public List<PlanNode> children() {
    return this.children;
  }

  protected void addChild(PlanNode planNode) {
    this.children.add(planNode);
  }


}
