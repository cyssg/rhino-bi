package org.rhinodata.rhinobi.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础请求
 *
 * @author chenye
 * @date 2023-02-01
 */
@ToString
@Data
@Schema(name = "基请求", description = "基请求")
public class BaseRequest implements Serializable {

    /**
     * 请求用户
     */
    @Schema(description = "请求用户")
    private String requestUser;

    /**
     * 请求时间
     */
    private Date requestTime = new Date();


}
