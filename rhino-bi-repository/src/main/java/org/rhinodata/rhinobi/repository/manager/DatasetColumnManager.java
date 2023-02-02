package org.rhinodata.rhinobi.repository.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.rhinodata.rhinobi.repository.entity.RbDatasetColumnEntity;
import org.rhinodata.rhinobi.repository.mapper.RbDatasetColumnMapper;
import org.springframework.stereotype.Component;

/**
 * @author chenye
 * @date 2023-02-01
 */
@Component
public class DatasetColumnManager extends ServiceImpl<RbDatasetColumnMapper, RbDatasetColumnEntity> {
}
