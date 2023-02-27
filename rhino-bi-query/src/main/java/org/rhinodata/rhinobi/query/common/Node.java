package org.rhinodata.rhinobi.query.common;

import org.rhinodata.rhinobi.common.api.RbException;

import java.util.List;

/**
 * @author chenye
 * @date 2023-02-22
 */
public interface Node {

    List<? extends Node> children();

    default <R, C> R accept(NodeVisitor<R, C> visitor, C context) {
        return visitor.visitNode(this, context);
    }

    default <T extends Node> T unwrap(Class<T> clazz) {
        if (clazz.isInstance(this)) {
            return clazz.cast(this);
        }
        throw new RbException("错误的对象类型转换：[" + this.getClass().getName() + "]->[" + clazz.getName() + "]");
    }

}
