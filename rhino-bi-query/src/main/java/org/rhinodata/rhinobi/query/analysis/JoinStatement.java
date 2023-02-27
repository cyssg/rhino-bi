package org.rhinodata.rhinobi.query.analysis;

import java.util.List;

/**
 * @author chenye
 * @date 2023-02-22
 */
public class JoinStatement extends Statement {

  private final Statement left;
  private final List<JoinRelation> joinRelation;

  public JoinStatement(Statement left, List<JoinRelation> joinRelation) {
    this.left = left;
    this.joinRelation = joinRelation;
  }

  public enum JoinType {
    LEFT,
    RIGHT,
    FULL_OUTER
  }

  public class JoinRelation {
    private JoinType joinType;
    private Statement right;
    private List<JoinPredicate> joinPredicates;
  }

  public class JoinPredicate {}
}
