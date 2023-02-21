package org.rhinodata.rhinobi.query.dsl;

/**
 * @author chenye
 * @date 2023-02-03
 */
public class OrderBy extends AbstractDql {

    @Override
    public <R, C> R accept(DqlVisitor<R, C> visitor, C context) {
        return null;
    }
}
