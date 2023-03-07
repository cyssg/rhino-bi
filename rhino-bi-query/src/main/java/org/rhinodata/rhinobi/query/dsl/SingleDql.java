package org.rhinodata.rhinobi.query.dsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.pulsar.shade.javax.annotation.concurrent.Immutable;

/**
 * SingleDql 封装的是一个最原子的查询模式，面向单数据集查询
 *
 * @author chenye
 * @date 2023-02-03
 */
@Immutable
public class SingleDql extends Dql {

  /** 对哪个数据集进行查询 */
  private DatasetSpec dataset;

  /** 需要哪些维度 */
  private DimensionSpec dimension;

  /** 需要哪些指标 */
  private MetricSpec metric;

  /** 过滤条件 */
  private FilterSpec filter;

  /** 排序方式 */
  private OrderBySpec orderBy;

  /** 条数限制 */
  private LimitSpec limit;

  @JsonCreator
  public SingleDql(
      @JsonProperty("dataset") DatasetSpec dataset,
      @JsonProperty("dimension") DimensionSpec dimension,
      @JsonProperty("metric") MetricSpec metric,
      @JsonProperty("filter") FilterSpec filter,
      @JsonProperty("orderBy") OrderBySpec orderBy,
      @JsonProperty("limit") LimitSpec limit) {
    this.dataset = dataset;
    this.dimension = dimension;
    this.metric = metric;
    this.filter = filter;
    this.orderBy = orderBy;
    this.limit = limit;
    this.addChild(dataset)
        .addChild(dimension)
        .addChild(metric)
        .addChild(filter)
        .addChild(orderBy)
        .addChild(limit);
  }

  @JsonProperty
  public DatasetSpec getDataset() {
    return dataset;
  }

  @JsonProperty
  public DimensionSpec getDimension() {
    return dimension;
  }

  @JsonProperty
  public MetricSpec getMetric() {
    return metric;
  }

  @JsonProperty
  public FilterSpec getFilter() {
    return filter;
  }

  @JsonProperty
  public OrderBySpec getOrderBy() {
    return orderBy;
  }

  @JsonProperty
  public LimitSpec getLimit() {
    return limit;
  }
}
