package org.rhinodata.rhinobi.query.analysis;

import lombok.Getter;

/**
 * @author chenye
 * @date 2023-02-25
 */
@Getter
public class QueryStatement extends Statement {

  private final ProjectStatement projectStatement;
  private final Statement fromStatement;
  private final WhereStatement whereStatement;
  private final OrderByStatement orderByStatement;

  public QueryStatement(
      ProjectStatement projectStatement,
      Statement fromStatement,
      WhereStatement whereStatement,
      OrderByStatement orderByStatement) {
    this.projectStatement = projectStatement;
    this.fromStatement = fromStatement;
    this.whereStatement = whereStatement;
    this.orderByStatement = orderByStatement;
    this.addChild(projectStatement)
        .addChild(fromStatement)
        .addChild(whereStatement)
        .addChild(orderByStatement);
  }
}
