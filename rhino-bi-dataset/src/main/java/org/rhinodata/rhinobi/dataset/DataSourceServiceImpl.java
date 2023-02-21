package org.rhinodata.rhinobi.dataset;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.rhinodata.rhinobi.dataset.convert.DataSourceConvert;
import org.rhinodata.rhinobi.dataset.domain.QueryDataSource;
import org.rhinodata.rhinobi.dataset.request.DataSourceCreateRequest;
import org.rhinodata.rhinobi.repository.entity.RbDataSourceEntity;
import org.rhinodata.rhinobi.repository.manager.DataSourceManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenye
 * @date 2023-02-21
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    private DataSourceManager dataSourceManager;

    public DataSourceServiceImpl(DataSourceManager dataSourceManager) {
        this.dataSourceManager = dataSourceManager;
    }

    @Override
    public QueryDataSource getByUuid(String uuid) {
        RbDataSourceEntity dataSourceEntity = dataSourceManager.getById(uuid);
        Assert.notNull(dataSourceEntity, "数据集不存在：{}", uuid);
        QueryDataSource queryDataSource = DataSourceConvert.convert(dataSourceEntity);
        return queryDataSource;
    }

    @Override
    public QueryDataSource getByName(String name) {
        QueryWrapper<RbDataSourceEntity> queryRequest=new QueryWrapper();
        RbDataSourceEntity entity = dataSourceManager.getOne(queryRequest.eq("name",name));
        return DataSourceConvert.convert(entity);
    }

    @Override
    public void create(DataSourceCreateRequest request) {
        RbDataSourceEntity rbDataSourceEntity = DataSourceConvert.convert(request);
        dataSourceManager.save(rbDataSourceEntity);
    }
}
