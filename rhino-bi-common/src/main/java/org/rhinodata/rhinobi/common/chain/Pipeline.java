package org.rhinodata.rhinobi.common.chain;

/**
 * @author chenye
 * @date 2023-02-06
 */
public interface Pipeline<T> {

    void exec();

    Pipeline<T> addHandler(Handler<T> handler);

}
