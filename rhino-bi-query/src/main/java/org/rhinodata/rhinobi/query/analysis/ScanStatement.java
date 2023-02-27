package org.rhinodata.rhinobi.query.analysis;

import lombok.Getter;

/**
 * 对应获取数据集的相关纤细
 *
 * @author chenye
 * @date 2023-02-22
 */
@Getter
public class ScanStatement extends Statement {

  private final String datasetUuid;
  private final String datasetName;
  private final String datasetCode;
  private final String datasourceName;

  public ScanStatement(
      String datasetUuid, String datasetName, String datasetCode, String datasourceName) {
    this.datasetUuid = datasetUuid;
    this.datasetName = datasetName;
    this.datasetCode = datasetCode;
    this.datasourceName = datasourceName;
  }
}
