package org.rhinodata.rhinobi.query.dsl;

import org.rhinodata.rhinobi.query.common.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-03
 */
public abstract class Dql implements Node {

  private final List<Dql> children = new ArrayList<>();

  @Override
  public List<Dql> children() {
    return children;
  }

  public Dql addChild(Dql dql) {
    if (Objects.nonNull(dql)) {
      this.children().add(dql);
    }
    return this;
  }
}
