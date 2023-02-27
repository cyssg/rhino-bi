package org.rhinodata.rhinobi.metadata.convert;

import cn.hutool.core.lang.UUID;
import org.rhinodata.rhinobi.common.tool.constant.Constant;
import org.rhinodata.rhinobi.common.tool.utils.StringUtil;
import org.rhinodata.rhinobi.metadata.domain.DataSourceType;
import org.rhinodata.rhinobi.metadata.domain.JdbcQueryDataSource;
import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;
import org.rhinodata.rhinobi.metadata.request.DataSourceCreateRequest;
import org.rhinodata.rhinobi.repository.entity.RbDataSourceEntity;

import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-21
 */
public class DataSourceConvert {

    public static QueryDataSource convert(RbDataSourceEntity rbDataSourceEntity) {
        if (Objects.isNull(rbDataSourceEntity)) {
            return null;
        }
        DataSourceType dataSourceType = DataSourceType.getByCode(rbDataSourceEntity.getType());
        switch (dataSourceType) {
            case JDBC -> {
                return convertJdbcDataSource(rbDataSourceEntity);
            }
            default -> {
                return null;
            }
        }
    }

    private static JdbcQueryDataSource convertJdbcDataSource(RbDataSourceEntity rbDataSourceEntity) {
        JdbcQueryDataSource jdbcQueryDataSource = new JdbcQueryDataSource(
                rbDataSourceEntity.getUuid(),
                rbDataSourceEntity.getName(),
                DataSourceType.getByCode(rbDataSourceEntity.getType()),
                rbDataSourceEntity.getConfig());
        return jdbcQueryDataSource;
    }

    public static RbDataSourceEntity convert(DataSourceCreateRequest request) {
        RbDataSourceEntity rbDataSourceEntity = new RbDataSourceEntity();
        rbDataSourceEntity.setUuid(UUID.fastUUID().toString());
        rbDataSourceEntity.setName(request.getName());
        rbDataSourceEntity.setType(request.getType());
        rbDataSourceEntity.setConfig(request.getConfig());
        rbDataSourceEntity.setCreateUser(StringUtil.isNotBlank(request.getRequestUser()) ? request.getRequestUser() : Constant.SYSTEM);
        rbDataSourceEntity.setUpdateUser(StringUtil.isNotBlank(request.getRequestUser()) ? request.getRequestUser() : Constant.SYSTEM);
        rbDataSourceEntity.setCreateTime(request.getRequestTime());
        rbDataSourceEntity.setUpdateTime(request.getRequestTime());
        return rbDataSourceEntity;
    }
}
