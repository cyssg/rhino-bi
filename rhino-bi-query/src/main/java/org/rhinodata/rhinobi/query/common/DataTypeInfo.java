package org.rhinodata.rhinobi.query.common;


import org.rhinodata.rhinobi.common.enums.DataType;

import java.util.Objects;

/**
 * @author chenye158@hellobike.com
 * @date 2022-07-28 11:18:38
 */

public record DataTypeInfo(String name, DataType type) {

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return Objects.equals(this.name, ((DataTypeInfo) obj).name);
    }

    public DataTypeInfo rename() {
        return new DataTypeInfo(this.name + "_0", this.type);
    }
}
