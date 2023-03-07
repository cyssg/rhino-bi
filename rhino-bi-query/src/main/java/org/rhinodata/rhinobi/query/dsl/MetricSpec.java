package org.rhinodata.rhinobi.query.dsl;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import org.apache.pulsar.shade.javax.annotation.concurrent.Immutable;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-04
 */
@Immutable
public class MetricSpec extends Dql implements Iterable<ReferenceSpec> {

  private final List<ReferenceSpec> items;

  @JsonCreator
  public MetricSpec(@JsonProperty("items") List<ReferenceSpec> items) {
    this.items = ImmutableList.copyOf(Assert.notNull(items, "items 为NULL"));
  }

  @JsonProperty
  public List<ReferenceSpec> getItems() {
    return items;
  }

  @NotNull
  @Override
  public Iterator<ReferenceSpec> iterator() {
    return items.iterator();
  }

  @Override
  public void forEach(Consumer<? super ReferenceSpec> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<ReferenceSpec> spliterator() {
    return Iterable.super.spliterator();
  }
}
