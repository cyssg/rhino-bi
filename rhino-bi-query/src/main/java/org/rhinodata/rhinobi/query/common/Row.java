package org.rhinodata.rhinobi.query.common;

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
public class Row implements Iterable<Value> {

    private final List<Value> values;

    public Row() {
        this.values = new ArrayList<>();
    }

    public Value getValue(Integer pos) {
        return values.get(pos);
    }

    public Row addValue(Value value) {
        this.values.add(value);
        return this;
    }

    public Row merge(Row row) {
        this.values.addAll(row.values);
        return this;
    }

    public int length() {
        return values.size();
    }

    public void remove(int position) {
        this.values.remove(position);
    }


    private void removeValue(Row row, int begin, List<Integer> exclude) {
        Iterator iterator = row.values.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            if (exclude.contains(begin)) {
                iterator.remove();
            }
            begin++;
        }
    }

    @NotNull
    @Override
    public Iterator<Value> iterator() {
        return values.iterator();
    }

    @Override
    public void forEach(Consumer<? super Value> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Value> spliterator() {
        return Iterable.super.spliterator();
    }

}
