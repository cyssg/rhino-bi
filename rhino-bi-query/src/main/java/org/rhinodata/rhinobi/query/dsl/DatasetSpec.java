package org.rhinodata.rhinobi.query.dsl;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenye
 * @date 2023-02-06
 */
@Data
@Builder
public class DatasetSpec extends AbstractDql {

    /**
     * 要数据集的UUID
     */
    private String datasetUuid;

    @Override
    public <R, C> R accept(DqlVisitor<R, C> visitor, C context) {
        return visitor.visitDatasetSpec(this,context);
    }

}
