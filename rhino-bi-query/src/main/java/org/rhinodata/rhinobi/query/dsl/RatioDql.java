package org.rhinodata.rhinobi.query.dsl;

import lombok.Data;

import java.util.List;

/**
 * @author chenye
 * @date 2023-02-22
 */
@Data
public class RatioDql extends Dql {

  private final SingleDql singleDql;
  private List<Ratio> targets;

  public RatioDql(SingleDql singleDql) {
    this.singleDql = singleDql;
    this.children().add(singleDql);
  }

  public class Ratio {
    String type;
    String dateTime;
  }
}
