package org.rhinodata.rhinobi.query.common;

/**
 * @author chenye
 * @date 2023-02-22
 */
public interface NodeVisitor<R,C> {
    R visitNode(Node node,C context);
}
