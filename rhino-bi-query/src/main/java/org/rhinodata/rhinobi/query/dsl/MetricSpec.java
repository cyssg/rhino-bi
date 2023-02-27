package org.rhinodata.rhinobi.query.dsl;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-04
 */
@Builder
@Data
public class MetricSpec extends Dql implements Iterable<Expression> {

  private final List<Expression> expressionList;

  public MetricSpec(List<Expression> expressionList) {
    this.expressionList =
        ImmutableList.copyOf(Assert.notNull(expressionList, "expressionList 为NULL"));
  }

  @NotNull
  @Override
  public Iterator<Expression> iterator() {
    return expressionList.iterator();
  }

  @Override
  public void forEach(Consumer<? super Expression> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<Expression> spliterator() {
    return Iterable.super.spliterator();
  }
}
