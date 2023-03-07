package org.rhinodata.rhinobi.query.analysis;

import org.apache.pulsar.shade.javax.annotation.concurrent.Immutable;
import org.rhinodata.rhinobi.query.common.RelationalType;

import java.util.List;

/**
 * @author chenye
 * @date 2023-03-03
 */
@Immutable
public class RelationalStatement extends PredicateStatement {

  private final RelationalType relationalType;
  private final List<PredicateStatement> statementList;

  public RelationalStatement(RelationalType relationalType, List<PredicateStatement> statementList) {
    this.relationalType = relationalType;
    this.statementList = statementList;
  }

  public RelationalType getRelationalType() {
    return relationalType;
  }

  public List<PredicateStatement> getStatementList() {
    return statementList;
  }
}
