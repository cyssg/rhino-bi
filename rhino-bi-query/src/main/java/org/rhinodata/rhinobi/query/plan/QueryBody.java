package org.rhinodata.rhinobi.query.plan;

import org.rhinodata.rhinobi.dataset.domain.QueryDataSource;

/**
 * 一个最原始的查询，直接面向数据源的查询
 * 封装了执行的Sql Code
 * @author chenye
 * @date 2023-02-06
 */
public class QueryBody extends PlanNode {

    private String code;
    private String dataSourceName;

    public QueryBody() {

    }
}
