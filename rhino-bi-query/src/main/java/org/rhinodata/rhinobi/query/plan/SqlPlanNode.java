package org.rhinodata.rhinobi.query.plan;

import lombok.Getter;

/**
 * 执行SQL的Node
 *
 * @author chenye
 * @date 2023-02-23
 */
@Getter
public class SqlPlanNode extends PlanNode {

  /** 执行的Sql语句 */
  private final String sql;
  /** 执行的数据源 */
  private final String datasourceName;

  public SqlPlanNode(Projects projects, String sql, String datasourceName) {
    super(projects);
    this.sql = sql;
    this.datasourceName = datasourceName;
  }
}
