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
  private final FilterStatement filterStatement;
  private final OrderByStatement orderByStatement;

  public QueryStatement(
      ProjectStatement projectStatement,
      Statement fromStatement,
      FilterStatement filterStatement,
      OrderByStatement orderByStatement) {
    this.projectStatement = projectStatement;
    this.fromStatement = fromStatement;
    this.filterStatement = filterStatement;
    this.orderByStatement = orderByStatement;
    this.addChild(projectStatement)
        .addChild(fromStatement)
        .addChild(filterStatement)
        .addChild(orderByStatement);
  }
}
