package org.rhinodata.rhinobi.metadata.domain;

import java.util.List;

/**
 * 数据集
 *
 * @author chenye
 * @date 2022-12-12 15:21:50
 */
public interface Dataset {

  /**
   * 数据集名称
   *
   * @return
   */
  String getName();

  /**
   * 数据集展示名称
   *
   * @return
   */
  String getDisplayName();

  /**
   * 数据集的实现代码
   *
   * @return
   */
  String getCode();

  /**
   * 数据集类型：COMMON、DIM_MODEL
   *
   * @return
   */
  DatasetType getType();

  /**
   * 数据集的数据源名称
   *
   * @return
   */
  String getDatasourceName();

  /**
   * 数据集的数据源类型
   *
   * @return
   */
  DataSourceType getDatasourceType();

  /**
   * 数据集展示名称
   *
   * @return
   */
  String getOnlineVersion();

  /**
   * 数据集的列
   *
   * @return
   */
  List<Column> getColumns();

  /**
   * 维度
   *
   * @return
   */
  List<Dimension> getDimensions();

  /**
   * 指标
   *
   * @return
   */
  List<Metric> getMetrics();

  /**
   * 根据名称获得指标
   *
   * @param name
   * @return
   */
  Metric getMetric(String name);

  /**
   * 根据指标名字获得维度
   *
   * @param name
   * @return
   */
  Dimension getDimension(String name);

  /**
   * 根据名称获取Column信息
   *
   * @param name
   * @return
   */
  Column getColumn(String name);

  /**
   * 获取DT维度
   *
   * @return
   */
  Dimension getDtDimension();
}
