package org.rhinodata.rhinobi.common.clone;

/**
 * @author chenye
 * @date 2023-02-25
 */
public interface Cloneable<T> extends java.lang.Cloneable {
  T clone();
}
