package org.rhinodata.rhinobi.query.dsl;

/**
 * @author chenye
 * @date 2023-02-03
 */
public abstract class DqlVisitor<R, C> {


    protected R visitSingleDql(SingleDql singleDql, C context) {
        return null;
    }

    protected R visitFilter(Filter filter, C context) {
        return null;
    }

    protected R visitOrderBy(OrderBy orderBy, C context) {
        return null;
    }

    protected R visitLimit(Limit limit, C context) {
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


    protected R visitDql(Dql dql, C context) {
        if (dql instanceof SingleDql) {
            return visitSingleDql((SingleDql) dql, context);
        }
        if (dql instanceof DatasetSpec) {
            return visitDatasetSpec((DatasetSpec) dql, context);
        }
        if (dql instanceof MetricSpec) {
            return visitMetricSpec((MetricSpec) dql, context);
        }
        if (dql instanceof DimensionSpec) {
            return visitDimensionSpec((DimensionSpec) dql, context);
        }
        return (R) dql;
    }

}
