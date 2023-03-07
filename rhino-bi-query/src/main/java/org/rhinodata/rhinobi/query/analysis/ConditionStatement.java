package org.rhinodata.rhinobi.query.analysis;

import org.rhinodata.rhinobi.query.common.OpType;

import javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-03-03
 */
@Immutable
public class ConditionStatement extends PredicateStatement {

  private final SymbolReference reference;
  private final OpType opType;
  private final String value;

  public ConditionStatement(SymbolReference reference, OpType opType, String value) {
    this.reference = reference;
    this.opType = opType;
    this.value = value;
  }

  public SymbolReference getReference() {
    return reference;
  }

  public OpType getOpType() {
    return opType;
  }

  public String getValue() {
    return value;
  }
}
