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
 * @date 2023-01-31
 */
@Getter
@AllArgsConstructor
public enum ColumnType {

    /**
     * DIMENSION
     */
    DIMENSION("DIMENSION", "维度"),
    /**
     * METRIC
     */
    METRIC("METRIC", "指标"),
    /**
     * CALCULATE
     */
    CALCULATE("CALCULATE", "计算列");

    private static final Map<String, ColumnType> ALL_CODE_ENUM = Stream.of(ColumnType.values())
            .collect(Collectors.toMap(ColumnType::getCode, Function.identity()));
    /**
     * 值
     */
    final String code;
    /**
     * 信息描述
     */
    final String desc;

    public static ColumnType getByCode(String code) {
        Objects.requireNonNull(code,"code required");
        ColumnType columnType = ALL_CODE_ENUM.get(code.toUpperCase());
        Objects.requireNonNull(code,"Error columnType code");
        return columnType;
    }
}
