package org.rhinodata.rhinobi.metadata;

import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.metadata.domain.Dimension;
import org.rhinodata.rhinobi.metadata.domain.Metric;
import org.rhinodata.rhinobi.metadata.request.DatasetCreateRequest;


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

    /**
     * 根据UUID获取指标ID
     * @param uuid
     * @return
     */
    Metric getMetric(String uuid);


    /**
     * 根据UUID获取维度ID
     * @param uuid
     * @return
     */
    Dimension getDimension(String uuid);

}
