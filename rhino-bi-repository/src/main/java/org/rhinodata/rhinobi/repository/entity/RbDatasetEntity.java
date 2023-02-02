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

package org.rhinodata.rhinobi.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rhinodata.rhinobi.repository.base.BaseEntity;

/**
 * rb_dataset
 *
 * @author chenye
 * @date 2022-12-12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rb_dataset")
public class RbDatasetEntity extends BaseEntity {

    @TableId
    private String uuid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "display_name")
    private String displayName;

    @TableField(value = "code")
    private String code;

    @TableField(value = "online_version")
    private String onlineVersion;

}