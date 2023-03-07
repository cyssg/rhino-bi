package org.rhinodata.rhinobi.web.controller;

import org.rhinodata.rhinobi.common.api.R;
import org.rhinodata.rhinobi.common.tool.constant.Constant;
import org.rhinodata.rhinobi.query.QueryService;
import org.rhinodata.rhinobi.query.common.QueryResult;
import org.rhinodata.rhinobi.query.dsl.Dql;
import org.rhinodata.rhinobi.query.dsl.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenye
 * @date 2023-03-03
 */
@Controller
@RequestMapping(value = "/query_test")
public class TestController {

    private final QueryService queryService;

    @Autowired
    public TestController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/code")
    public String code() {
        return "query";
    }

  @PostMapping(value = "/doQuery")
  @ResponseBody
  public R<QueryResult> query(@RequestParam String querySql) {
        Dql dql = Dql.fromString(querySql);
        Query query = new Query(null, Constant.SYSTEM, dql);
        QueryResult result = queryService.query(query);
        return R.data(result);
    }
}
