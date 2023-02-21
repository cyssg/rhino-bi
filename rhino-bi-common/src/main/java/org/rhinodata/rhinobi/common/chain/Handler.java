package org.rhinodata.rhinobi.common.chain;

/**
 * @author chenye
 * @date 2023-02-06
 */
public interface Handler<T> {

  void doHandler(T context);

}
