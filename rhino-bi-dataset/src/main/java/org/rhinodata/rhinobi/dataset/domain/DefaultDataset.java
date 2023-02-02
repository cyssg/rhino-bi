package org.rhinodata.rhinobi.dataset.domain;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-01
 */
@Setter
public class DefaultDataset implements Dataset{

    private String name;
    private String displayName;
    private String code;
    private String onlineVersion;
    private List<Column> columns = new ArrayList<>();
    private List<Dimension> dimensions = new ArrayList<>();

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
        return null;
    }

    public void addColumn(Column column){
        this.columns.add(column);
        switch(column.getColumnType()){
            case DIMENSION -> {
                this.dimensions.add((Dimension)column);
            }
            default -> {

            }
        }
    }
}
