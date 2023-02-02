package org.rhinodata.rhinobi.dataset;

import cn.hutool.core.collection.CollectionUtil;
import org.rhinodata.rhinobi.dataset.convert.DatasetConvert;
import org.rhinodata.rhinobi.dataset.domain.Dataset;
import org.rhinodata.rhinobi.dataset.request.DatasetCreateRequest;
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
public class DatasetServiceImpl implements DatasetService{

    @Autowired
    private DatasetManager datasetManager;
    @Autowired
    private DatasetColumnManager datasetColumnManager;

    @Override
    public Dataset getDataset(String uuid) {
        RbDatasetEntity datasetEntity = datasetManager.getById(uuid);
        Map<String,Object> map = new HashMap<>();
        map.put("dataset_uuid",datasetEntity.getUuid());
        List<RbDatasetColumnEntity> columnEntities = datasetColumnManager.listByMap(map);
        Dataset dataset = DatasetConvert.convert(datasetEntity,columnEntities);
        return dataset;
    }

    @Override
    public void create(DatasetCreateRequest request) {
        RbDatasetEntity datasetEntity = DatasetConvert.convert(request);
        datasetManager.save(datasetEntity);
        if (!CollectionUtil.isEmpty(request.getDatasetColumns())){
            request.getDatasetColumns().forEach(r->{
                RbDatasetColumnEntity columnEntity = DatasetConvert.convert(r);
                columnEntity.setDatasetUuid(datasetEntity.getUuid());
                datasetColumnManager.save(columnEntity);
            });
        }
    }


}
