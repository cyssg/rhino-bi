package org.rhinodata.rhinobi.query.dsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.pulsar.shade.javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-02-06
 */
@Immutable
public class DatasetSpec extends Dql {

  /** 要数据集的UUID */
  private String uuid;

  @JsonCreator
  public DatasetSpec(@JsonProperty("uuid") String uuid) {
    this.uuid = uuid;
  }

  @JsonProperty
  public String getUuid() {
    return uuid;
  }
}
