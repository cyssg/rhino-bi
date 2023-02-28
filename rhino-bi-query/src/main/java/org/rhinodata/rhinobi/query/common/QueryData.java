package org.rhinodata.rhinobi.query.common;

import org.rhinodata.rhinobi.query.plan.Projects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenye
 * @date 2023-02-27
 */
public interface QueryData extends QueryStatus {

  RowType getRowType();

  RowSet getRowSet();

  default List<Map<String, Object>> toMap() {
    List<Map<String, Object>> list = new ArrayList<>();
    RowType rowType = getRowType();
    getRowSet()
        .forEach(
            row -> {
              Map<String, Object> line = new LinkedHashMap<>();
              list.add(line);
              for (int i = 0; i < row.length(); i++) {
                DataTypeInfo dataTypeInfo = rowType.get(i);
                line.put(
                    dataTypeInfo.name(),
                    row.getValue(i) == null
                        ? null
                        : row.getValue(i).unwrap(dataTypeInfo.type().toClazz()));
              }
            });
    return list;
  }

  default List<Map<String, Object>> toMap(Projects projects) {
    List<Map<String, Object>> list = new ArrayList<>();
    RowType rowType = getRowType();
    getRowSet()
        .forEach(
            row -> {
              Map<String, Object> line = new LinkedHashMap<>();
              list.add(line);
              projects.forEach(
                  project -> {
                    int pos = rowType.getPosition(project.getAlias());
                    DataTypeInfo dataTypeInfo = rowType.get(project.getAlias());
                    line.put(
                        project.getAlias(),
                        row.getValue(pos) == null
                            ? null
                            : row.getValue(pos).unwrap(dataTypeInfo.type().toClazz()));
                  });
            });
    return list;
  }
}
