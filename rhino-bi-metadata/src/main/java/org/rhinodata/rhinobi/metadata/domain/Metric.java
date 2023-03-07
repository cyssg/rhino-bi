package org.rhinodata.rhinobi.metadata.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.rhinodata.rhinobi.common.enums.DataType;

import javax.annotation.concurrent.Immutable;

/**
 * @author chenye
 * @date 2023-01-31
 */
@Immutable
@Schema(name = "指标", description = "指标")
public class Metric extends AbstractColumn {

  public Metric(String uuid, String name, String displayName, String code, DataType dataType) {
    super(uuid, name, displayName, code, dataType, ColumnType.METRIC);
  }
}
