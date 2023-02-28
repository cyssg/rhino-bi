package org.rhinodata.rhinobi.query.common;

import cn.hutool.core.lang.UUID;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-27
 */
public class RowSet implements Iterable<Row> {

  /** 一次性内存中RowSet 的唯一ID */
  private final String id;

  private final List<Row> rowList;

  public RowSet() {
    this.id = UUID.fastUUID().toString(true);
    this.rowList = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public RowSet addRow(Row row) {
    this.rowList.add(row);
    return this;
  }

  @NotNull
  @Override
  public Iterator<Row> iterator() {
    return rowList.iterator();
  }

  @Override
  public void forEach(Consumer<? super Row> action) {
    Iterable.super.forEach(action);
  }

  @Override
  public Spliterator<Row> spliterator() {
    return Iterable.super.spliterator();
  }

  public int size() {
    return rowList.size();
  }
}
