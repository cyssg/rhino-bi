package org.rhinodata.rhinobi.query.dsl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.annotation.OrderBy;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.logging.Filter;

/**
 * SingleDql 封装的是一个最原子的查询模式，面向单数据集查询
 *
 * @author chenye
 * @date 2023-02-03
 */
@Data
@ToString
public class SingleDql extends AbstractDql {

    /**
     * 对哪个数据集进行查询
     */
    private DatasetSpec datasetSpec;

    /**
     * 需要哪些维度
     */
    private List<DimensionSpec> dimensions;

    /**
     * 需要哪些指标
     */
    private List<MetricSpec> metrics;

    /**
     * 过滤条件
     */
    private Filter filter;

    /**
     * 排序方式
     */
    private OrderBy orderBy;

    /**
     * 条数限制
     */
    private Limit limit;

    public SingleDql(DatasetSpec datasetSpec, List<DimensionSpec> dimensions, List<MetricSpec> metrics, Filter filter, OrderBy orderBy, Limit limit) {
        this.datasetSpec = datasetSpec;
        this.dimensions = dimensions;
        this.metrics = metrics;
        this.filter = filter;
        this.orderBy = orderBy;
        this.limit = limit;
        if (Objects.nonNull(datasetSpec)) {
            children().add(datasetSpec);
        }
        if (CollectionUtil.isNotEmpty(dimensions)) {
            children().addAll(dimensions);
        }
        if (CollectionUtil.isNotEmpty(metrics)) {
            children().addAll(metrics);
        }
    }

    @Override
    public <R, C> R accept(DqlVisitor<R, C> visitor, C context) {
        return visitor.visitSingleDql(this, context);
    }
}
