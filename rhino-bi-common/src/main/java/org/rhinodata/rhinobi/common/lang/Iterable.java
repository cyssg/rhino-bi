package org.rhinodata.rhinobi.common.lang;

import cn.hutool.core.lang.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author chenye
 * @date 2023-02-26
 */
public class Iterable<T> implements java.lang.Iterable {

  private List<T> list;

  public Iterable() {
    this.list = new ArrayList<>();
  }

  public Iterable(List<T> list) {
    this.list = Assert.notNull(list, "入参list 不得为空");
  }

  public int size() {
    return list.size();
  }

  public void add(T t) {
    this.list.add(t);
  }

  public T get(int i) {
    return this.list.get(i);
  }

  @Override
  public Iterator iterator() {
    return list.iterator();
  }

  @Override
  public void forEach(Consumer action) {
    java.lang.Iterable.super.forEach(action);
  }

  @Override
  public Spliterator spliterator() {
    return java.lang.Iterable.super.spliterator();
  }
}
