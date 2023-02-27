package org.rhinodata.rhinobi.web.controller.vo;

import lombok.Data;
import org.rhinodata.rhinobi.metadata.domain.Dataset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-01-31
 */
@Data
public class DatasetVO implements Serializable {
    private String name;
    private String displayName;
    private String code;
    private String type;
    private String datasourceName;
    private String datasourceType;
    private String onlineVersion;
    private List<MetricVO> metrics = new ArrayList<>();
    private List<DimensionVO> dimensions = new ArrayList<>();


    public static DatasetVO from(Dataset dataset) {
        DatasetVO datasetVO = new DatasetVO();
        if (Objects.nonNull(dataset)) {
            datasetVO.setName(dataset.getName());
            datasetVO.setDisplayName(dataset.getDisplayName());
            datasetVO.setOnlineVersion(dataset.getOnlineVersion());
            datasetVO.setCode(dataset.getCode());
            datasetVO.setType(dataset.getType().getCode());
            if (Objects.nonNull(dataset.getDatasourceName())) {
                datasetVO.setDatasourceName(dataset.getDatasourceName());
            }
            if (Objects.nonNull(dataset.getDatasourceType())) {
                datasetVO.setDatasourceType(dataset.getDatasourceType().getCode());
            }
            dataset.getDimensions().forEach(d -> {
                DimensionVO dimensionVO = new DimensionVO();
                dimensionVO.setName(d.getName());
                dimensionVO.setCode(d.getCode());
                dimensionVO.setDisplayName(d.getDisplayName());
                dimensionVO.setDataType(d.getDataType().getCode());
                datasetVO.getDimensions().add(dimensionVO);
            });
        }
        return datasetVO;
    }

    @Data
    public static class DimensionVO {
        private String name;
        private String displayName;
        private String code;
        private String dataType;
    }

    @Data
    public class MetricVO {

    }
}
