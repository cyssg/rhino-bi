package org.rhinodata.rhinobi.query.dsl;

import java.util.List;

/**
 * @author chenye
 * @date 2023-02-03
 */
public interface Dql {

    /**
     * 子查询模式
     *
     * @return
     */
    List<Dql> children();

    /**
     * 接受访问者
     *
     * @param visitor
     * @param context
     * @param <R>
     * @param <C>
     * @return
     */
    <R, C> R accept(DqlVisitor<R, C> visitor, C context);
}
