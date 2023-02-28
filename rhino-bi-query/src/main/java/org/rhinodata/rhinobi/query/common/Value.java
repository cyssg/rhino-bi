package org.rhinodata.rhinobi.query.common;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-27
 */
public record Value(Object value) implements Comparable<Value> {

    public static Value of(Object value) {
        return new Value(value);
    }

    public boolean isNull() {
        return Objects.isNull(value);
    }

    public Value getValue() {
        return this;
    }


    public String toString() {
        if (Objects.nonNull(value)) {
            return value.toString();
        }
        return null;
    }


    public <T extends Object> T unwrap(Class<T> clazz) {
        if (Objects.isNull(value)) {
            return null;
        }
        if (clazz.isInstance(this.value)) {
            return clazz.cast(this.value);
        }
        if (Number.class.isInstance(this.value)) {
            return (T) new BigDecimal(this.value.toString());
        }
        return null;
    }

    @Override
    public int compareTo(Value o) {
        if (this == o) {
            return 0;
        }
        if (Objects.equals(o.getValue(), this.value)) {
            return 0;
        }
        return -1;
    }

    public boolean isEquals(Value o) {
        return compareTo(o) == 0;
    }
}
