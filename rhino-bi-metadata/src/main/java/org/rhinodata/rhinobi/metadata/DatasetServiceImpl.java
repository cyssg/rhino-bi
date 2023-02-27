package org.rhinodata.rhinobi.metadata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import org.rhinodata.rhinobi.metadata.convert.DatasetConvert;
import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.metadata.request.DatasetCreateRequest;
import org.rhinodata.rhinobi.repository.entity.RbDatasetColumnEntity;
import org.rhinodata.rhinobi.repository.entity.RbDatasetEntity;
import org.rhinodata.rhinobi.repository.manager.DatasetColumnManager;
import org.rhinodata.rhinobi.repository.manager.DatasetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenye
 * @date 2023-01-31
 */
@Service
public class DatasetServiceImpl implements DatasetService {

    private DatasetManager datasetManager;
    private DatasetColumnManager datasetColumnManager;

    @Autowired
    public DatasetServiceImpl(DatasetManager datasetManager, DatasetColumnManager datasetColumnManager) {
        this.datasetManager = datasetManager;
        this.datasetColumnManager = datasetColumnManager;
    }

    @Override
    public Dataset getDataset(String uuid) {
        RbDatasetEntity datasetEntity = datasetManager.getById(uuid);
        Assert.notNull(datasetEntity, "数据集不存在：{}", uuid);
        Map<String, Object> map = new HashMap<>();
        map.put("dataset_uuid", datasetEntity.getUuid());
        List<RbDatasetColumnEntity> columnEntities = datasetColumnManager.listByMap(map);
        Dataset dataset = DatasetConvert.convert(datasetEntity, columnEntities);
        return dataset;
    }

    @Override
    public void create(DatasetCreateRequest request) {
        RbDatasetEntity datasetEntity = DatasetConvert.convert(request);
        datasetManager.save(datasetEntity);
        if (!CollectionUtil.isEmpty(request.getDatasetColumns())) {
            request.getDatasetColumns().forEach(r -> {
                RbDatasetColumnEntity columnEntity = DatasetConvert.convert(r);
                columnEntity.setDatasetUuid(datasetEntity.getUuid());
                datasetColumnManager.save(columnEntity);
            });
        }
    }
}
