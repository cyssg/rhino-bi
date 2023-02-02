package org.rhinodata.rhinobi.web.controller.vo;

import lombok.Data;
import org.rhinodata.rhinobi.dataset.domain.Dataset;

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
    private String onlineVersion;
    private List<MetricVO> metrics = new ArrayList<>();
    private List<DimensionVO> dimensions = new ArrayList<>();



    public  DatasetVO fromDataset(Dataset dataset) {
        if (Objects.nonNull(dataset)) {
            this.setName(dataset.getName());
            this.setDisplayName(dataset.getDisplayName());
            this.setOnlineVersion(dataset.getOnlineVersion());
            this.setCode(dataset.getCode());
            dataset.getDimensions().forEach(d->{
                DimensionVO dimensionVO = new DimensionVO();
                dimensionVO.setName(d.getName());
                dimensionVO.setCode(d.getCode());
                dimensionVO.setDisplayName(d.getDisplayName());
                dimensionVO.setDataType(d.getDataType().getCode());
                this.dimensions.add(dimensionVO);
            });
        }
        return this;
    }

    @Data
    public class MetricVO{

    }

    @Data
    public class DimensionVO{
        private String name;
        private String displayName;
        private String code;
        private String dataType;
    }
}
