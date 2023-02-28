package org.rhinodata.rhinobi.query.common;

import org.jetbrains.annotations.NotNull;
import org.rhinodata.rhinobi.query.QueryException;

import java.util.*;

/**
 * @author chenye
 * @date 2023-02-27
 */
public class RowType implements Iterable<DataTypeInfo> {

  private final List<DataTypeInfo> dataTypeInfos;
  private final Map<String, DataTypeInfo> dataTypeInfoMap;

  public RowType() {
    this.dataTypeInfos = new ArrayList<>();
    this.dataTypeInfoMap = new HashMap<>();
  }

  public RowType addDataTypeInfo(DataTypeInfo dataTypeInfo) {
    this.dataTypeInfos.add(dataTypeInfo);
    this.dataTypeInfoMap.put(dataTypeInfo.name(), dataTypeInfo);
    return this;
  }

  public void removeDataTypeInfo(DataTypeInfo dataTypeInfo) {
    this.dataTypeInfos.remove(dataTypeInfo);
    this.dataTypeInfoMap.remove(dataTypeInfo.name());
  }

  public Integer getPositionAllowNull(String name) {
    for (int i = 0; i < dataTypeInfos.size(); i++) {
      if (Objects.equals(dataTypeInfos.get(i).name(), name)) {
        return i;
      }
    }
    return null;
  }

  public Integer getPosition(String name) {
    Integer pos = getPositionAllowNull(name);
    if (Objects.isNull(pos)) {
      throw new QueryException("列名不存在 ： " + name);
    }
    return pos;
  }

  public int length() {
    return dataTypeInfos.size();
  }

  public DataTypeInfo get(int pos) {
    return dataTypeInfos.get(pos);
  }

  public DataTypeInfo get(String name) {
    return dataTypeInfoMap.get(name);
  }

  public boolean contains(String name) {
    return dataTypeInfoMap.containsKey(name);
  }

  public RowType merger(RowType rowType) {
    rowType.forEach(
        dataTypeInfo -> {
          if (!this.contains(dataTypeInfo.name())) {
            this.addDataTypeInfo(new DataTypeInfo(dataTypeInfo.name(), dataTypeInfo.type()));
          } else {
            this.addDataTypeInfo(dataTypeInfo.rename());
          }
        });
    return this;
  }

  public void renewWith(RowType rowType) {
    this.dataTypeInfos.clear();
    this.dataTypeInfoMap.clear();
    rowType.forEach(
        dataTypeInfo -> {
          this.addDataTypeInfo(new DataTypeInfo(dataTypeInfo.name(), dataTypeInfo.type()));
        });
  }

  public RowType clone() {
    RowType rowType = new RowType();
    for (DataTypeInfo dti : dataTypeInfos) {
      rowType.addDataTypeInfo(new DataTypeInfo(dti.name(), dti.type()));
    }
    return rowType;
  }

  @NotNull
  @Override
  public Iterator<DataTypeInfo> iterator() {
    return dataTypeInfos.iterator();
  }

  @Override
  public String toString() {
    StringBuilder toString = new StringBuilder("RowType{\ndataTypeInfos=[\n");

    dataTypeInfos.forEach(
        d -> {
          toString.append(d.name()).append(":").append(d.type()).append("\n");
        });
    toString.append("\n]\n}");
    return toString.toString();
  }
}
