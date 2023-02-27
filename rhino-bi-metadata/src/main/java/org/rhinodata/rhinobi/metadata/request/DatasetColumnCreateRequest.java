package org.rhinodata.rhinobi.metadata.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.rhinodata.rhinobi.common.api.BaseRequest;

/**
 * @author chenye
 * @date 2023-02-01
 */
@Data
@Schema(name = "数据集列的创建请求对象", description = "数据集列的创建请求对象")
public class DatasetColumnCreateRequest extends BaseRequest {

    @Schema(description = "数据集UUID")
    private String datasetUuid;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "展示名称")
    private String displayName;

    @Schema(description = "Code")
    private String code;

    @Schema(description = "数据类型",allowableValues = {"string","double","bigint"})
    private String dataType;

    @Schema(description = "类型",allowableValues = {"dimension","metric","calculate"})
    private String columnType;
}
