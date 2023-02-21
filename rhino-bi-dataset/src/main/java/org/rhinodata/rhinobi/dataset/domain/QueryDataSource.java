package org.rhinodata.rhinobi.dataset.domain;


import org.rhinodata.rhinobi.common.api.RbException;

/**
 * @author chenye
 * @date 2023-02-21
 */
public interface QueryDataSource {

    /**
     * 获取数据源的UUID
     *
     * @return
     */
    String getUuid();

    /**
     * 获取数据源的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取数据源的类型
     *
     * @return
     */
    DataSourceType getType();

    /**
     * 获取数据源的配置
     *
     * @return
     */
    String getConfig();

    /**
     * 子类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    default <T extends QueryDataSource> T unwrap(Class<T> clazz) {
        if (clazz.isInstance(this)) {
            return clazz.cast(this);
        }
        throw new RbException("无法cast成类 ： " + clazz.getName());
    }
}
