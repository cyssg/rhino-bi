package org.rhinodata.rhinobi.query.analysis;

import org.rhinodata.rhinobi.common.enums.DataType;

import javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-03-03
 */
@Immutable
public class SymbolReference extends Statement {

  private final String uuid;
  private final String name;
  private final String expr;
  private final String alias;
  private final DataType dataType;

  public SymbolReference(String uuid, String name, String expr, String alias, DataType dataType) {
    this.uuid = uuid;
    this.name = name;
    this.expr = expr;
    this.alias = alias;
    this.dataType = dataType;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public String getExpr() {
    return expr;
  }

  public String getAlias() {
    return alias;
  }

  public DataType getDataType() {
    return dataType;
  }
}
