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
public class DimensionSpec extends Dql implements Iterable<DimensionSpec.DimensionSpecItem> {

  private final List<DimensionSpecItem> items;

  @JsonCreator
  public DimensionSpec(@JsonProperty("items") List<DimensionSpecItem> items) {
    this.items = ImmutableList.copyOf(Assert.notNull(items, "items 为NULL"));
  }

  @NotNull
  @Override
  public Iterator<DimensionSpecItem> iterator() {
    return items.iterator();
  }

  @Override
  public void forEach(Consumer<? super DimensionSpecItem> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<DimensionSpecItem> spliterator() {
    return Iterable.super.spliterator();
  }

  @JsonProperty
  public List<DimensionSpecItem> getDimensionSpecItems() {
    return items;
  }

  @Immutable
  public static class DimensionSpecItem extends ReferenceSpec {

    /** 是否是日期条件字段 */
    private final boolean isDt;

    @JsonCreator
    public DimensionSpecItem(
        @JsonProperty("uuid") String uuid,
        @JsonProperty("name") String name,
        @JsonProperty("expr") String expr,
        @JsonProperty("alias") String alias,
        @JsonProperty("isDt") boolean isDt) {
      super(uuid, name, expr, alias);
      this.isDt = isDt;
    }

    @JsonProperty
    public boolean isDt() {
      return isDt;
    }
  }
}
