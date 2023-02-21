package org.rhinodata.rhinobi.query.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-07
 */
public abstract class AbstractDql implements Dql {

    private final List<Dql> children = new ArrayList<>();

    @Override
    public List<Dql> children() {
        return children;
    }

    @Override
    public <R, C> R accept(DqlVisitor<R, C> visitor, C context) {
        return null;
    }
}
