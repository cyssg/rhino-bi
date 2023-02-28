package org.rhinodata.rhinobi.query.dsl;

import org.rhinodata.rhinobi.query.analysis.Statement;

/**
 * @author chenye
 * @date 2023-02-22
 */
public class Expression extends Statement {

  private final String expr;
  private final String alias;

  public Expression(String expr) {
    this.expr = expr;
    this.alias = null;
  }

  public Expression(String expr, String alias) {
    this.expr = expr;
    this.alias = alias;
  }

  public String getExpr() {
    return expr;
  }

  public String getAlias() {
    return alias;
  }
}
