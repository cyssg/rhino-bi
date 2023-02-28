package org.rhinodata.rhinobi.query.executor;

import cn.hutool.core.io.IoUtil;
import org.rhinodata.rhinobi.common.enums.DataType;
import org.rhinodata.rhinobi.metadata.domain.JdbcQueryDataSource;
import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;
import org.rhinodata.rhinobi.query.QueryException;
import org.rhinodata.rhinobi.query.common.*;
import org.rhinodata.rhinobi.query.plan.SqlPlanNode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * @author chenye
 * @date 2023-02-21
 */
public class JdbcQueryExecutor implements QueryExecutor<QueryData> {

  private final QueryDataSource queryDataSource;
  private final SqlPlanNode sqlPlanNode;

  public JdbcQueryExecutor(QueryDataSource queryDataSource, SqlPlanNode sqlPlanNode) {
    this.queryDataSource = queryDataSource;
    this.sqlPlanNode = sqlPlanNode;
  }

  @Override
  public QueryData execute() {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    StringBuffer resultStr = new StringBuffer();
    try {

      JdbcQueryDataSource jdbcQueryDataSource = queryDataSource.unwrap(JdbcQueryDataSource.class);
      connection = jdbcQueryDataSource.getDataSource().getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sqlPlanNode.getSql());
      ResultSetMetaData metaData = resultSet.getMetaData();
      RowType rowType = toRowType(metaData);
      RowSet rowSet = new RowSet();
      while (resultSet.next()) {
        Row row = new Row();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
          // 添加行结果
          row.addValue(Value.of(resultSet.getObject(i)));
        }
        rowSet.addRow(row);
      }
      return new CommonQueryData(rowType, rowSet, null);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    } finally {
      IoUtil.close(resultSet);
      IoUtil.close(statement);
      IoUtil.close(connection);
    }
  }

  private RowType toRowType(ResultSetMetaData resultSetMetaData) {
    RowType rowType = new RowType();
    try {
      for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
        rowType.addDataTypeInfo(
            new DataTypeInfo(
                resultSetMetaData.getColumnLabel(i),
                DataType.fromMySQLType(resultSetMetaData.getColumnTypeName(i))));
      }
    } catch (Exception ex) {
      throw new QueryException(ex.getMessage(), ex);
    }
    return rowType;
  }
}
