package org.rhinodata.rhinobi.metadata.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.rhinodata.rhinobi.common.enums.DataType;

import javax.annotation.concurrent.Immutable;

/**
 * 维度
 *
 * @author chenye
 * @date 2023-01-31
 */
@Immutable
@Schema(name = "维度", description = "维度")
public class Dimension extends AbstractColumn {

  public Dimension(String uuid, String name, String displayName, String code, DataType dataType) {
    super(uuid, name, displayName, code, dataType, ColumnType.DIMENSION);
  }
}
