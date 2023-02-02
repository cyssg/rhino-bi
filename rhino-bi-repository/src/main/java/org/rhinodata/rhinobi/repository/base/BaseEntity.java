/* Copyright 2022-2032 RhinoData Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rhinodata.rhinobi.repository.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.rhinodata.rhinobi.common.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cyssg
 * @date 2022-12-12 16:51:12
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 创建人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "创建人")
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    @Schema(description = "创建时间")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "更新人")
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    @Schema(description = "更新时间")
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 状态[1:正常]
     */
    @Schema(description = "业务状态")
    @TableField(value = "status")
    private Integer status;

    /**
     * 状态[0:未删除,1:删除]
     */
    @TableLogic
    @Schema(description = "是否已删除")
    private Integer isDeleted;
}
