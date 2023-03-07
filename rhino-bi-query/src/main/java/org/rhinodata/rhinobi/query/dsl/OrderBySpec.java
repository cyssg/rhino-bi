package org.rhinodata.rhinobi.query.dsl;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;
import org.apache.pulsar.shade.javax.annotation.concurrent.Immutable;
import org.jetbrains.annotations.NotNull;
import org.rhinodata.rhinobi.query.common.SortType;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-03
 */
@Immutable
public class OrderBySpec extends Dql implements Iterable<OrderBySpec.OrderBySpecItem> {

  private final List<OrderBySpecItem> items;

  @JsonCreator
  public OrderBySpec(@JsonProperty("items") List<OrderBySpecItem> items) {
    this.items = ImmutableList.copyOf(Assert.notNull(items, "Order By items 为NULL"));
  }

  @NotNull
  @Override
  public Iterator<OrderBySpecItem> iterator() {
    return items.iterator();
  }

  @Override
  public void forEach(Consumer<? super OrderBySpecItem> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<OrderBySpecItem> spliterator() {
    return Iterable.super.spliterator();
  }

  @Immutable
  public static class OrderBySpecItem {
    private final ReferenceSpec expression;
    private final SortType sortType;

    @JsonCreator
    public OrderBySpecItem(
        @JsonProperty("expression") ReferenceSpec expression,
        @JsonProperty("sortType") String sortType) {
      this.expression = expression;
      this.sortType = SortType.getByCode(sortType);
    }

    @JsonProperty
    public ReferenceSpec getExpression() {
      return expression;
    }

    @JsonProperty
    public SortType getSortType() {
      return sortType;
    }
  }
}
