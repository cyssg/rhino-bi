package org.rhinodata.rhinobi.metadata;

import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;
import org.rhinodata.rhinobi.metadata.request.DataSourceCreateRequest;

/**
 * @author chenye
 * @date 2023-02-21
 */
public interface DataSourceService {

    /**
     * 根据UUID获得数据源
     *
     * @param uuid
     * @return
     */
    QueryDataSource getByUuid(String uuid);

    /**
     * 根据名称获得数据源
     *
     * @param name
     * @return
     */
    QueryDataSource getByName(String name);

    /**
     * 创建数据源
     *
     * @param request
     */
    void create(DataSourceCreateRequest request);


}
