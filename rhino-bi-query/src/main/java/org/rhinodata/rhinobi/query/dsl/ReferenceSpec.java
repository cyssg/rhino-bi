package org.rhinodata.rhinobi.query.dsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.pulsar.shade.javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-02-22
 */
@Immutable
public class ReferenceSpec {

  private final String uuid;
  private final String name;
  private final String expr;
  private final String alias;

  @JsonCreator
  public ReferenceSpec(
      @JsonProperty("uuid") String uuid,
      @JsonProperty("name") String name,
      @JsonProperty("expr") String expr,
      @JsonProperty("alias") String alias) {
    this.uuid = uuid;
    this.name = name;
    this.expr = expr;
    this.alias = alias;
  }

  @JsonProperty
  public String getUuid() {
    return uuid;
  }

  @JsonProperty
  public String getName() {
    return name;
  }

  @JsonProperty
  public String getExpr() {
    return expr;
  }

  @JsonProperty
  public String getAlias() {
    return alias;
  }

  @Override
  public String toString() {
    return "Expression{"
        + "uuid='"
        + uuid
        + '\''
        + ", name='"
        + name
        + '\''
        + ", expr='"
        + expr
        + '\''
        + ", alias='"
        + alias
        + '\''
        + '}';
  }
}
