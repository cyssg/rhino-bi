package org.rhinodata.rhinobi.metadata.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenye
 * @date 2023-02-21
 */
@Getter
@AllArgsConstructor
public enum DataSourceType {

    /**
     * JDBC
     */
    JDBC("JDBC", "JDBC"),
    /**
     * HBASE
     */
    HBASE("HBASE", "HBASE"),
    /**
     * CLICKHOUSE
     */
    CLICKHOUSE("CLICKHOUSE", "CLICKHOUSE");

    private static final Map<String, DataSourceType> ALL_CODE_ENUM = Stream.of(DataSourceType.values())
            .collect(Collectors.toMap(DataSourceType::getCode, Function.identity()));
    /**
     * 值
     */
    final String code;
    /**
     * 信息描述
     */
    final String desc;

    public static DataSourceType getByCode(String code) {
        Objects.requireNonNull(code, "code required");
        DataSourceType dataSourceType = ALL_CODE_ENUM.get(code.toUpperCase());
        Objects.requireNonNull(code, "Error dataSourceType code");
        return dataSourceType;
    }
}
