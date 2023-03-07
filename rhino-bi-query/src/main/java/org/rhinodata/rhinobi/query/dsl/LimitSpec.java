package org.rhinodata.rhinobi.query.dsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-02-03
 */
@Immutable
public class LimitSpec extends Dql {

  private final int offset;
  private final int size;

  @JsonCreator
  public LimitSpec(@JsonProperty("offset") int offset, @JsonProperty("size") int size) {
    this.offset = offset;
    this.size = size;
  }

  @JsonProperty
  public int getOffset() {
    return offset;
  }

  @JsonProperty
  public int getSize() {
    return size;
  }
}
