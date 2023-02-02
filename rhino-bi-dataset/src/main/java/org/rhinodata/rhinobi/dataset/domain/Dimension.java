package org.rhinodata.rhinobi.dataset.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

/**
 * 维度
 * @author chenye
 * @date 2023-01-31
 */
@Schema(name = "维度", description = "维度")
@Setter
public class Dimension extends AbstractColumn{

    public Dimension(ColumnType columnType) {
        super(columnType);
    }
}
