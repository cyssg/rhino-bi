package org.rhinodata.rhinobi.query.analysis;

import org.rhinodata.rhinobi.query.common.Node;
import org.rhinodata.rhinobi.query.common.NodeVisitor;

/**
 * @author chenye
 * @date 2023-02-23
 */
public abstract class StatementVisitor<R, C> implements NodeVisitor<R, C> {

  public R visitQueryStatement(QueryStatement queryStatement, C context) {
    return null;
  }

  public R visitProjectStatement(ProjectStatement projectsStatement, C context) {
    return null;
  }

  public R visitJoinStatement(JoinStatement joinStatement, C context) {
    return null;
  }

  public R visitFilterStatement(FilterStatement filterStatement, C context) {
    return null;
  }

  public R visitPredicateStatement(PredicateStatement predicateStatement, C context) {
    if (predicateStatement instanceof ConditionStatement) {
      return visitConditionStatement(predicateStatement.unwrap(ConditionStatement.class), context);
    } else if (predicateStatement instanceof RelationalStatement) {
      return visitRelationalStatement(
          predicateStatement.unwrap(RelationalStatement.class), context);
    } else {
      return null;
    }
  }

  public R visitConditionStatement(ConditionStatement conditionStatement, C context) {
    return null;
  }

  public R visitRelationalStatement(RelationalStatement relationalStatement, C context) {
    return null;
  }

  public R visitOrderByStatement(OrderByStatement orderByStatement, C context) {
    return null;
  }

  public R visitScanStatement(ScanStatement scanStatement, C context) {
    return null;
  }

  public R visitNode(Node node, C context) {
    if (node instanceof QueryStatement) {
      return visitQueryStatement(node.unwrap(QueryStatement.class), context);
    }
    if (node instanceof ProjectStatement) {
      return visitProjectStatement(node.unwrap(ProjectStatement.class), context);
    }
    if (node instanceof JoinStatement) {
      return visitJoinStatement(node.unwrap(JoinStatement.class), context);
    }
    if (node instanceof FilterStatement) {
      return visitFilterStatement(node.unwrap(FilterStatement.class), context);
    }
    if (node instanceof PredicateStatement) {
      return visitPredicateStatement(node.unwrap(PredicateStatement.class), context);
    }
    if (node instanceof ConditionStatement) {
      return visitConditionStatement(node.unwrap(ConditionStatement.class), context);
    }
    if (node instanceof RelationalStatement) {
      return visitRelationalStatement(node.unwrap(RelationalStatement.class), context);
    }
    if (node instanceof OrderByStatement) {
      return visitOrderByStatement(node.unwrap(OrderByStatement.class), context);
    }
    if (node instanceof ScanStatement) {
      return visitScanStatement(node.unwrap(ScanStatement.class), context);
    }
    return null;
  }
}
