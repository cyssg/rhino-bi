package org.rhinodata.rhinobi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rhinodata.rhinobi.common.api.R;
import org.rhinodata.rhinobi.common.tool.constant.Constant;
import org.rhinodata.rhinobi.query.QueryService;
import org.rhinodata.rhinobi.query.common.QueryResult;
import org.rhinodata.rhinobi.query.dsl.Dql;
import org.rhinodata.rhinobi.query.dsl.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenye
 * @date 2023-02-07
 */
@RestController
@Tag(name = "查询")
@RequestMapping(value = "/query")
public class QueryController {

  private final QueryService queryService;

  @Autowired
  public QueryController(QueryService queryService) {
    this.queryService = queryService;
  }



  @PostMapping(value = "/byCode")
  @Operation(summary = "通过query code 查询")
  public R<QueryResult> query(@RequestParam String querySql, @RequestParam String queryId) {
    Dql dql = Dql.fromString(querySql);
    Query query = new Query(queryId, Constant.SYSTEM, dql);
    QueryResult result = queryService.query(query);
    return R.data(result);
  }
}
