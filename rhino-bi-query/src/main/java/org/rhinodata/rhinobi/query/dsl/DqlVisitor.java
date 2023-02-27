package org.rhinodata.rhinobi.query.dsl;

import org.rhinodata.rhinobi.query.common.Node;
import org.rhinodata.rhinobi.query.common.NodeVisitor;

/**
 * @author chenye
 * @date 2023-02-03
 */
public abstract class DqlVisitor<R, C> implements NodeVisitor<R, C> {


    protected R visitSingleDql(SingleDql singleDql, C context) {
        return null;
    }

    protected R visitRatioDql(RatioDql ratioDql, C context) {
        return null;
    }

    protected R visitFilterSpec(FilterSpec filterSpec, C context) {
        return null;
    }

    protected R visitOrderBySpec(OrderBySpec orderBySpec, C context) {
        return null;
    }

    protected R visitLimitSpec(LimitSpec limitSpec, C context) {
        return null;
    }

    protected R visitMetricSpec(MetricSpec metricSpec, C context) {
        return null;
    }

    protected R visitDimensionSpec(DimensionSpec dimensionSpec, C context) {
        return null;
    }

    protected R visitDatasetSpec(DatasetSpec datasetSpec, C context) {
        return null;
    }


    public R visitNode(Node node, C context) {
        if (node instanceof RatioDql) {
            return visitRatioDql(node.unwrap(RatioDql.class), context);
        }
        if (node instanceof SingleDql) {
            return visitSingleDql(node.unwrap(SingleDql.class), context);
        }
        if (node instanceof DatasetSpec) {
            return visitDatasetSpec(node.unwrap(DatasetSpec.class), context);
        }
        if (node instanceof MetricSpec) {
            return visitMetricSpec(node.unwrap(MetricSpec.class), context);
        }
        if (node instanceof DimensionSpec) {
            return visitDimensionSpec(node.unwrap(DimensionSpec.class), context);
        }
        if (node instanceof FilterSpec) {
            return visitFilterSpec(node.unwrap(FilterSpec.class), context);
        }
        if (node instanceof OrderBySpec) {
            return visitOrderBySpec(node.unwrap(OrderBySpec.class), context);
        }
        if (node instanceof LimitSpec) {
            return visitLimitSpec(node.unwrap(LimitSpec.class), context);
        }
        return null;
    }

}
