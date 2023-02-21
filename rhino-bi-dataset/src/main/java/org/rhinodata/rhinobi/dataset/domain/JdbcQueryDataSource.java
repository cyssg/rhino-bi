package org.rhinodata.rhinobi.dataset.domain;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.rhinodata.rhinobi.common.api.RbException;
import org.rhinodata.rhinobi.common.tool.utils.StringUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-21
 */
public class JdbcQueryDataSource implements QueryDataSource {

    private final static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
    private final String uuid;
    private final String name;
    private final DataSourceType type;
    private final String config;
    private final Config configBean;


    public JdbcQueryDataSource(String uuid, String name, DataSourceType type, String config) {
        this.uuid = uuid;
        this.name = name;
        this.type = type;
        this.config = config;
        this.configBean = JSONUtil.toBean(config, Config.class);
    }

    public synchronized DataSource getDataSource() {
        try {
            DataSource dataSource = dataSourceMap.get(this.name);
            if (Objects.isNull(dataSource)) {
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setDriverClassName(configBean.getDriverClassName());
                druidDataSource.setUrl(configBean.getDbUrl());
                druidDataSource.setUsername(configBean.getUsername());
                druidDataSource.setPassword(configBean.getPassword());
                //配置项
                druidDataSource.setInitialSize(configBean.getInitialSize());
                druidDataSource.setMinIdle(configBean.getMinIdle());
                druidDataSource.setMaxActive(configBean.getMaxActive());
                druidDataSource.setMaxWait(configBean.getMaxWait());
                druidDataSource.setTimeBetweenEvictionRunsMillis(configBean.getTimeBetweenEvictionRunsMillis());
                druidDataSource.setMinEvictableIdleTimeMillis(configBean.getMinEvictableIdleTimeMillis());
                druidDataSource.setValidationQuery(configBean.getValidationQuery());
                druidDataSource.setTestWhileIdle(configBean.isTestWhileIdle());
                druidDataSource.setTestOnBorrow(configBean.isTestOnBorrow());
                druidDataSource.setTestOnReturn(configBean.isTestOnReturn());
                druidDataSource.setPoolPreparedStatements(configBean.isPoolPreparedStatements());
                druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(configBean.getMaxPoolPreparedStatementPerConnectionSize());
                druidDataSource.init();
                dataSourceMap.put(this.getName(), druidDataSource);
                return druidDataSource;
            }
            return dataSource;
        } catch (Exception ex) {
            throw new RbException(StringUtil.format("[{}]-数据源加载失败", this.name), ex);
        }
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public DataSourceType getType() {
        return this.type;
    }

    @Override
    public String getConfig() {
        return this.config;
    }

    @Data
    class Config {
        private String dbUrl;
        private String username;
        private String password;
        private String driverClassName;
        // 设置连接池的初始连接数
        private int initialSize = 5;
        // 设置连接池的最小空闲连接数
        private int minIdle = 5;
        // 设置连接池的最大连接数
        private int maxActive = 20;
        // 设置连接池的最大等待时间
        private int maxWait = 30000;
        private int timeBetweenEvictionRunsMillis = 60000;
        private int minEvictableIdleTimeMillis = 300000;
        private String validationQuery = "select 1 from dual";
        private boolean testWhileIdle = true;
        private boolean testOnBorrow = false;
        private boolean testOnReturn = false;
        private boolean poolPreparedStatements = true;
        private int maxPoolPreparedStatementPerConnectionSize = 20;
    }
}
