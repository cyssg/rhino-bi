package org.rhinodata.rhinobi.query.analysis;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-24
 */
@Getter
public class ProjectStatement extends Statement implements Iterable<ProjectStatement.Item> {

  private final List<Item> items;

  public ProjectStatement() {
    this.items = new ArrayList<>();
  }

  public void addItem(Item item) {
    this.items.add(item);
  }

  public Item get(int i) {
    return items.get(i);
  }

  public int size() {
    return items.size();
  }

  public void merge(ProjectStatement projectStatement) {
    projectStatement.forEach(
        item -> {
          this.addItem(item);
        });
  }

  @NotNull
  @Override
  public Iterator<Item> iterator() {
    return items.iterator();
  }

  @Override
  public void forEach(Consumer<? super Item> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<Item> spliterator() {
    return Iterable.super.spliterator();
  }

  @Override
  public Statement clone() {
    return null;
  }

  public record Item(String expr, String alias, boolean isGroupBy) {}
}
