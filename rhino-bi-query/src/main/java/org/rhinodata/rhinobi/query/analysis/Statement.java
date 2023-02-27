package org.rhinodata.rhinobi.query.analysis;

import org.rhinodata.rhinobi.common.clone.Cloneable;
import org.rhinodata.rhinobi.query.common.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-22
 */
public abstract class Statement implements Node, Cloneable<Statement> {

  private final List<Statement> children;

  public Statement() {
    this.children = new ArrayList<>();
  }

  @Override
  public List<Statement> children() {
    return this.children;
  }

  public Statement addChild(Statement statement) {
    if (Objects.nonNull(statement)) {
      this.children.add(statement);
    }
    return this;
  }

  @Override
  public Statement clone() {
    return null;
  }
}
