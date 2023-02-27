package org.rhinodata.rhinobi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rhinodata.rhinobi.common.api.R;
import org.rhinodata.rhinobi.query.QueryService;
import org.rhinodata.rhinobi.query.common.QueryResult;
import org.rhinodata.rhinobi.query.dsl.Query;
import org.rhinodata.rhinobi.query.request.QueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping(value = "/query")
    @Operation(summary = "通过query对象查询")
    public R<QueryResult> query(@RequestBody QueryRequest queryRequest) {
        Query query = queryRequest.convert();
        QueryResult result = queryService.query(query);
        return R.data(result);
    }
}
