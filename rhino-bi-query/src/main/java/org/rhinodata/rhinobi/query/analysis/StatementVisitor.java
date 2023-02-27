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

  public R visitWhereStatement(WhereStatement whereStatement, C context) {
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
    if (node instanceof WhereStatement) {
      return visitWhereStatement(node.unwrap(WhereStatement.class), context);
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
