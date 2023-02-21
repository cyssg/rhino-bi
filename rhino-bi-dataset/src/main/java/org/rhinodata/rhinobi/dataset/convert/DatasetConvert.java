package org.rhinodata.rhinobi.dataset.convert;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import org.rhinodata.rhinobi.common.enums.DataType;
import org.rhinodata.rhinobi.common.tool.constant.Constant;
import org.rhinodata.rhinobi.common.tool.utils.StringUtil;
import org.rhinodata.rhinobi.dataset.domain.*;
import org.rhinodata.rhinobi.dataset.request.DatasetColumnCreateRequest;
import org.rhinodata.rhinobi.dataset.request.DatasetCreateRequest;
import org.rhinodata.rhinobi.repository.entity.RbDatasetColumnEntity;
import org.rhinodata.rhinobi.repository.entity.RbDatasetEntity;

import java.util.Date;
import java.util.List;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_FORMAT;

/**
 * @author chenye
 * @date 2023-01-31
 */
public class DatasetConvert {

    public static Dataset convert(RbDatasetEntity rbDatasetEntity, List<RbDatasetColumnEntity> rbDatasetColumnEntityList) {
        DefaultDataset dataset = new DefaultDataset();
        dataset.setName(rbDatasetEntity.getName());
        dataset.setDisplayName(rbDatasetEntity.getDisplayName());
        dataset.setOnlineVersion(rbDatasetEntity.getOnlineVersion());
        dataset.setCode(rbDatasetEntity.getCode());
        dataset.setType(DatasetType.getByCode(rbDatasetEntity.getType()));
        dataset.setDatasourceName(rbDatasetEntity.getDatasourceName());
        dataset.setDatasourceType(DataSourceType.getByCode(rbDatasetEntity.getDatasourceType()));
        rbDatasetColumnEntityList.forEach(columnEntity -> {
            Column column = convert(columnEntity);
            dataset.addColumn(column);
        });
        return dataset;
    }

    public static RbDatasetEntity convert(DatasetCreateRequest request) {
        RbDatasetEntity entity = new RbDatasetEntity();
        entity.setName(request.getName());
        entity.setDisplayName(request.getDisplayName());
        entity.setUuid(UUID.fastUUID().toString());
        entity.setCode(request.getCode());
        entity.setType(request.getType());
        entity.setDatasourceName(request.getDatasourceName());
        entity.setDatasourceType(request.getDatasourceType());
        entity.setOnlineVersion(DateUtil.format(new Date(), PURE_DATETIME_FORMAT) + RandomUtil.randomNumbers(6));
        entity.setCreateUser(StringUtil.isNotBlank(request.getRequestUser()) ? request.getRequestUser() : Constant.SYSTEM);
        entity.setUpdateUser(StringUtil.isNotBlank(request.getRequestUser()) ? request.getRequestUser() : Constant.SYSTEM);
        entity.setCreateTime(request.getRequestTime());
        entity.setUpdateTime(request.getRequestTime());
        return entity;
    }

    public static RbDatasetColumnEntity convert(DatasetColumnCreateRequest request) {

        RbDatasetColumnEntity entity = new RbDatasetColumnEntity();
        entity.setDatasetUuid(request.getDatasetUuid());
        entity.setName(request.getName());
        entity.setDisplayName(request.getDisplayName());
        entity.setUuid(UUID.fastUUID().toString());
        entity.setCode(request.getCode());
        entity.setDataType(request.getDataType());
        entity.setColumnType(request.getColumnType());
        entity.setCreateUser(StringUtil.isNotBlank(request.getRequestUser()) ? request.getRequestUser() : Constant.SYSTEM);
        entity.setUpdateUser(StringUtil.isNotBlank(request.getRequestUser()) ? request.getRequestUser() : Constant.SYSTEM);
        entity.setCreateTime(request.getRequestTime());
        entity.setUpdateTime(request.getRequestTime());
        return entity;

//

    }

    public static Column convert(RbDatasetColumnEntity rbDatasetColumnEntity) {
        ColumnType columnType = ColumnType.getByCode(rbDatasetColumnEntity.getColumnType());
        switch (columnType) {
            case DIMENSION -> {
                Dimension dimension = new Dimension(columnType);
                dimension.setName(rbDatasetColumnEntity.getName());
                dimension.setDisplayName(rbDatasetColumnEntity.getDisplayName());
                dimension.setCode(rbDatasetColumnEntity.getCode());
                dimension.setDataType(DataType.getByCode(rbDatasetColumnEntity.getDataType()));
                return dimension;
            }
            default -> {
                return null;
            }
        }
    }
}
