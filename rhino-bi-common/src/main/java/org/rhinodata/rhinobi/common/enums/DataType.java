package org.rhinodata.rhinobi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenye
 * @date 2023-02-01
 */
@Getter
@AllArgsConstructor
public enum DataType {

    /**
     * STRING
     */
    STRING("STRING", "字符串"),
    /**
     * BIGINT
     */
    BIGINT("BIGINT", "整型"),
    /**
     * DOUBLE
     */
    DOUBLE("DOUBLE", "浮点数");

    private static final Map<String, DataType> ALL_CODE_ENUM = Stream.of(DataType.values())
            .collect(Collectors.toMap(DataType::getCode, Function.identity()));

    /**
     * 值
     */
    final String code;
    /**
     * 信息描述
     */
    final String desc;

    public static DataType getByCode(String code) {
        Objects.requireNonNull(code, "code required");
        DataType dataType = ALL_CODE_ENUM.get(code.toUpperCase());
        Objects.requireNonNull(code, "Error columnType code");
        return dataType;
    }


}
