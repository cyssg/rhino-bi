package org.rhinodata.rhinobi.web.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rhinodata.rhinobi.common.api.R;
import org.rhinodata.rhinobi.common.api.ResultCode;
import org.rhinodata.rhinobi.metadata.DataSourceService;
import org.rhinodata.rhinobi.metadata.domain.JdbcQueryDataSource;
import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;
import org.rhinodata.rhinobi.metadata.request.DataSourceCreateRequest;
import org.rhinodata.rhinobi.web.controller.vo.DataSourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * @author chenye
 * @date 2023-02-21
 */
@RestController
@RequestMapping(value = "/datasource")
@Tag(name = "数据源")
public class DataSourceController {

    private DataSourceService dataSourceService;

    @Autowired
    public DataSourceController(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @GetMapping(value = "/getByUuid")
    @Operation(summary = "根据UUID获得数据源")
    public R<DataSourceVO> getByUuid(@RequestParam String uuid) {
        QueryDataSource dataSource = dataSourceService.getByUuid(uuid);
        return R.data(DataSourceVO.from(dataSource));
    }

    @GetMapping(value = "/getByName")
    @Operation(summary = "根据name获得数据源")
    public R<DataSourceVO> getByName(@RequestParam String name) {
        QueryDataSource dataSource = dataSourceService.getByName(name);
        return R.data(DataSourceVO.from(dataSource));
    }


    @PostMapping(value = "/create")
    @Operation(summary = "创建数据源")
    public R<String> create(@RequestBody DataSourceCreateRequest dataSourceCreateRequest) {
        dataSourceService.create(dataSourceCreateRequest);
        return R.success(ResultCode.SUCCESS);
    }

    @PostMapping(value = "/query")
    @Operation(summary = "执行查询SQL")
    public R<String> execute(@RequestParam String name, @RequestParam String code) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuffer resultStr = new StringBuffer();
        try {
            QueryDataSource queryDataSource = dataSourceService.getByName(name);
            Assert.notNull(queryDataSource,"数据库不存在-{}",name);
            JdbcQueryDataSource jdbcQueryDataSource = queryDataSource.unwrap(JdbcQueryDataSource.class);
            connection = jdbcQueryDataSource.getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(code);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int colCnt = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= colCnt; i++) {
                    resultStr.append(resultSet.getString(i)).append(" | ");
                }
                resultStr.append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return R.fail(ResultCode.FAILURE, ex.getMessage());
        } finally {
            IoUtil.close(resultSet);
            IoUtil.close(statement);
            IoUtil.close(connection);
        }
        return R.data(resultStr.toString());
    }
}
