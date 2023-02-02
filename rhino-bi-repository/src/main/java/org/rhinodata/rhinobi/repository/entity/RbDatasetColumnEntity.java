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
 * @date 2023-02-01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rb_dataset_column")
public class RbDatasetColumnEntity  extends BaseEntity {

    @TableId
    private String uuid;

    @TableField(value = "dataset_uuid")
    private String datasetUuid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "display_name")
    private String displayName;

    @TableField(value = "code")
    private String code;

    @TableField(value = "column_type")
    private String columnType;

    @TableField(value = "data_type")
    private String dataType;
}
