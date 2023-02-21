package org.rhinodata.rhinobi.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rhinodata.rhinobi.repository.base.BaseEntity;

/**
 * @author chenye
 * @date 2023-02-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rb_datasource")
public class RbDataSourceEntity extends BaseEntity {

    @TableId
    private String uuid;

    @TableField(value = "name")
    private String name;

    @TableField(value = "type")
    private String type;

    @TableField(value = "config")
    private String config;

}
