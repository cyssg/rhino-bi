package org.rhinodata.rhinobi.metadata.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.rhinodata.rhinobi.common.api.BaseRequest;

import java.util.List;

/**
 * @author chenye
 * @date 2023-02-01
 */
@Data
@Schema(name = "数据集创建请求对象", description = "数据集创建请求对象")
public class DatasetCreateRequest extends BaseRequest {

    @Schema(description = "数据集名称")
    private String name;

    @Schema(description = "数据集展示名称")
    private String displayName;

    @Schema(description = "数据集Code")
    private String code;

    @Schema(description = "数据集类型")
    private String type;

    @Schema(description = "数据集的数据源名称")
    private String datasourceName;

    @Schema(description = "数据集的数据源类型")
    private String datasourceType;

    @Schema(description = "数据集列创建请求")
    private List<DatasetColumnCreateRequest> datasetColumns;
}
