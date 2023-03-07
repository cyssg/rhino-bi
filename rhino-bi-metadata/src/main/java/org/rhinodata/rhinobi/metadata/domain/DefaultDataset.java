package org.rhinodata.rhinobi.metadata.domain;

import cn.hutool.core.util.StrUtil;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-01
 */
@Setter
public class DefaultDataset implements Dataset {

    private String name;
    private String displayName;
    private String code;
    private DatasetType type;
    private String datasourceName;
    private DataSourceType datasourceType;
    private String onlineVersion;
    private List<Column> columns = new ArrayList<>();
    private List<Dimension> dimensions = new ArrayList<>();
    private List<Metric> metrics = new ArrayList<>();

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
    public DatasetType getType() {
        return this.type;
    }

    @Override
    public String getDatasourceName() {
        return this.datasourceName;
    }

    @Override
    public DataSourceType getDatasourceType() {
        return this.datasourceType;
    }

    @Override
    public String getOnlineVersion() {
        return this.onlineVersion;
    }

    @Override
    public List<Column> getColumns() {
        return this.columns;
    }

    @Override
    public List<Dimension> getDimensions() {
        return this.dimensions;
    }

    @Override
    public List<Metric> getMetrics() {
        return this.metrics;
    }

    @Override
    public Dimension getDimension(String name) {
        for (Dimension dimension : getDimensions()) {
            if (StrUtil.equals(name, dimension.getName())) {
                return dimension;
            }
        }
        return null;
    }

    @Override
    public Metric getMetric(String name) {
        for(Metric metric : getMetrics()){
            if(StrUtil.equals(name,metric.getName())){
                return metric;
            }
        }
        return null;
    }

    @Override
    public Column getColumn(String name) {
        for(Column column : getColumns()){
            if(StrUtil.equals(name,column.getName())){
                return column;
            }
        }
        return null;
    }

    @Override
    public Dimension getDtDimension() {
        for(Dimension dimension : getDimensions()){
            if (ColumnType.DT_DIMENSION.equals(dimension.getColumnType())){
                return dimension;
            }
        }
        return null;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
        switch (column.getColumnType()) {
            case DIMENSION,DT_DIMENSION -> {
                this.dimensions.add((Dimension) column);
            }
            case METRIC -> {
                this.metrics.add((Metric)column);
            }
            default -> {

            }
        }
    }
}
