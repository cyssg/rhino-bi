package org.rhinodata.rhinobi.query.dsl;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenye
 * @date 2023-02-04
 */
@Data
@Builder
public class DimensionSpec extends AbstractDql {

    private String dimensionCode;

    @Override
    public <R, C> R accept(DqlVisitor<R, C> visitor, C context) {
        return visitor.visitDimensionSpec(this,context);
    }
}
