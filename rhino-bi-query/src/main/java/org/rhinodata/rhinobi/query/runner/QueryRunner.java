package org.rhinodata.rhinobi.query.runner;

import cn.hutool.core.util.StrUtil;
import org.rhinodata.rhinobi.common.api.RbException;
import org.rhinodata.rhinobi.query.QueryContext;
import org.rhinodata.rhinobi.query.plan.PlanNode;
import org.rhinodata.rhinobi.query.plan.RecalculatePlanNode;
import org.rhinodata.rhinobi.query.plan.SqlPlanNode;

/**
 * @author chenye
 * @date 2023-02-20
 */
public interface QueryRunner {

  static QueryRunner getInstance(PlanNode planNode) {
    if (planNode instanceof SqlPlanNode) {
      return new SimpleSqlRunner(planNode.unwrap(SqlPlanNode.class));
    } else if (planNode instanceof RecalculatePlanNode) {
      return new RecalculateRunner(planNode.unwrap(RecalculatePlanNode.class));
    }
    throw new RbException(StrUtil.format("无法执行的planNode-{}", planNode.toString()));
  }

  void exec(QueryContext queryContext);
}
