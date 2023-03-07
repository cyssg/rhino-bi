package org.rhinodata.rhinobi.query.analysis;

import javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-02-22
 */
@Immutable
public class FilterStatement extends Statement {

  private final PredicateStatement predicate;

  public FilterStatement(PredicateStatement predicate) {
    this.predicate = predicate;
    this.addChild(predicate);
  }

  public PredicateStatement getPredicate() {
    return predicate;
  }
}
