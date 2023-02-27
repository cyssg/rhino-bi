package org.rhinodata.rhinobi.web.controller.vo;

import lombok.Data;
import org.rhinodata.rhinobi.metadata.domain.QueryDataSource;

/**
 * @author chenye
 * @date 2023-02-21
 */
@Data
public class DataSourceVO {
    private String uuid;
    private String name;
    private String type;
    private String config;

    public static DataSourceVO from(QueryDataSource queryDataSource) {
        DataSourceVO dataSourceVO = new DataSourceVO();
        dataSourceVO.setUuid(queryDataSource.getUuid());
        dataSourceVO.setName(queryDataSource.getName());
        dataSourceVO.setType(queryDataSource.getType().getCode());
        dataSourceVO.setConfig(queryDataSource.getConfig());
        return dataSourceVO;
    }
}
