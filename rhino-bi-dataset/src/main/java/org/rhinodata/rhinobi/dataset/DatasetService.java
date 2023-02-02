package org.rhinodata.rhinobi.dataset;

import org.rhinodata.rhinobi.dataset.domain.Dataset;
import org.rhinodata.rhinobi.dataset.request.DatasetCreateRequest;

/**
 * 数据集服务
 * @author chenye
 * @date 2023-01-31
 */
public interface DatasetService {

    /**
     * 根据UUID获得数据集对象
     * @param uuid
     * @return
     */
    Dataset getDataset(String uuid);

    /**
     * 创建数据集
     * @param datasetCreateRequest
     */
    void create(DatasetCreateRequest datasetCreateRequest);

}
