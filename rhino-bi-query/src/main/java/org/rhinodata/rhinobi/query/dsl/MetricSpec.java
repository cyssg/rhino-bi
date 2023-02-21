package org.rhinodata.rhinobi.query.dsl;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenye
 * @date 2023-02-04
 */
@Builder
@Data
public class MetricSpec extends AbstractDql {

    private String metricCode;

    @Override
    public <R, C> R accept(DqlVisitor<R, C> visitor, C context) {
        return visitor.visitMetricSpec(this, context);
    }
}
