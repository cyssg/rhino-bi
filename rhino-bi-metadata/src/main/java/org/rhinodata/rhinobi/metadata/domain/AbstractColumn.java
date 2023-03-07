package org.rhinodata.rhinobi.metadata.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.rhinodata.rhinobi.common.enums.DataType;

import javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-01-31
 */
@Immutable
@Schema(name = "数据集的列", description = "数据集的列")
public abstract class AbstractColumn implements Column {

  @Schema(description = "统一资源定位标识")
  private String uuid;

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

  public AbstractColumn(
      String uuid,
      String name,
      String displayName,
      String code,
      DataType dataType,
      ColumnType columnType) {
    this.uuid = uuid;
    this.name = name;
    this.displayName = displayName;
    this.code = code;
    this.dataType = dataType;
    this.columnType = columnType;
  }

  public String getUuid() {
    return uuid;
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
