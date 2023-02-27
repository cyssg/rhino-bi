package org.rhinodata.rhinobi.query.dsl;

import lombok.Data;
import lombok.ToString;

/**
 * SingleDql 封装的是一个最原子的查询模式，面向单数据集查询
 *
 * @author chenye
 * @date 2023-02-03
 */
@Data
@ToString
public class SingleDql extends Dql {

  /** 对哪个数据集进行查询 */
  private DatasetSpec datasetSpec;

  /** 需要哪些维度 */
  private DimensionSpec dimensionSpec;

  /** 需要哪些指标 */
  private MetricSpec metricSpec;

  /** 过滤条件 */
  private FilterSpec filterSpec;

  /** 排序方式 */
  private OrderBySpec orderBySpec;

  /** 条数限制 */
  private LimitSpec limitSpec;

  public SingleDql(
      DatasetSpec datasetSpec,
      DimensionSpec dimensionSpec,
      MetricSpec metricSpec,
      FilterSpec filterSpec,
      OrderBySpec orderBySpec,
      LimitSpec limitSpec) {
    this.datasetSpec = datasetSpec;
    this.dimensionSpec = dimensionSpec;
    this.metricSpec = metricSpec;
    this.filterSpec = filterSpec;
    this.orderBySpec = orderBySpec;
    this.limitSpec = limitSpec;
    this.addChild(datasetSpec)
        .addChild(dimensionSpec)
        .addChild(metricSpec)
        .addChild(filterSpec)
        .addChild(orderBySpec);
  }
}
