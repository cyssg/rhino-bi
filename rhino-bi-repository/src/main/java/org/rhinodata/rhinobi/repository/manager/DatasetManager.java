package org.rhinodata.rhinobi.repository.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.rhinodata.rhinobi.repository.entity.RbDatasetEntity;
import org.rhinodata.rhinobi.repository.mapper.RbDatasetMapper;
import org.springframework.stereotype.Component;

/**
 * @author chenye
 * @date 2023-01-31
 */
@Component
public class DatasetManager extends ServiceImpl<RbDatasetMapper,RbDatasetEntity> {

}
