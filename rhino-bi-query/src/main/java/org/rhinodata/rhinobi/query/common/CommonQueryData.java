package org.rhinodata.rhinobi.query.common;

/**
 * @author chenye
 * @date 2023-02-28
 */
public record CommonQueryData(RowType rowType,
                              RowSet rowSet,
                              QueryError queryError) implements QueryData {

    @Override
    public QueryError getError() {
        return this.queryError;
    }

    @Override
    public RowType getRowType() {
        return this.rowType;
    }

    @Override
    public RowSet getRowSet() {
        return this.rowSet;
    }
}
