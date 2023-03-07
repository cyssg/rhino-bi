package org.rhinodata.rhinobi.metadata.domain;

import org.rhinodata.rhinobi.common.enums.DataType;

/**
 * @author chenye
 * @date 2023-01-31
 */
public interface Column {

    String getUuid();

    ColumnType getColumnType();

    String getName();

    String getDisplayName();

    String getCode();

    DataType getDataType();


}
