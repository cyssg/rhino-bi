package org.rhinodata.rhinobi.dataset.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import org.rhinodata.rhinobi.common.enums.DataType;

/**
 * @author chenye
 * @date 2023-01-31
 */
@Schema(name = "数据集的列", description = "数据集的列")
@Setter
public abstract class AbstractColumn implements Column {

    @Schema(description = "名称")
    private String name;
    @Schema(description = "展示名称")
    private String displayName;
    @Schema(description = "code")
    private String code;
    @Schema(description = "数据类型")
    private DataType dataType;
    @Schema(description = "类型")
    private ColumnType columnType;


    public AbstractColumn(ColumnType columnType) {
        this.columnType = columnType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public DataType getDataType() {
        return this.dataType;
    }

    @Override
    public ColumnType getColumnType() {
        return this.columnType;
    }
}
