package org.rhinodata.rhinobi.dataset.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.rhinodata.rhinobi.common.api.BaseRequest;

/**
 * @author chenye
 * @date 2023-02-21
 */
@Data
@Schema(name = "数据源创建请求对象", description = "数据源创建请求对象")
public class DataSourceCreateRequest extends BaseRequest {

    @Schema(description = "数据源名称")
    private String name;

    @Schema(description = "数据源类型")
    private String type;

    @Schema(description = "数据源配置")
    private String config;
}
