package org.rhinodata.rhinobi.repository.manager;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.rhinodata.rhinobi.repository.entity.RbDataSourceEntity;
import org.rhinodata.rhinobi.repository.mapper.RbDataSourceMapper;
import org.springframework.stereotype.Component;

/**
 * @author chenye
 * @date 2023-02-21
 */
@Component
public class DataSourceManager extends ServiceImpl<RbDataSourceMapper, RbDataSourceEntity> {
}
