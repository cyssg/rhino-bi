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
 * @date 2023-02-22
 */
@Getter
@AllArgsConstructor
public enum DatasetType {
    /**
     * COMMON
     */
    COMMON("COMMON", "COMMON"),
    /**
     * DIM_MODEL
     */
    DIM_MODEL("DIM_MODEL", "DIM_MODEL");

    private static final Map<String, DatasetType> ALL_CODE_ENUM = Stream.of(DatasetType.values())
            .collect(Collectors.toMap(DatasetType::getCode, Function.identity()));
    /**
     * 值
     */
    final String code;
    /**
     * 信息描述
     */
    final String desc;

    public static DatasetType getByCode(String code) {
        Objects.requireNonNull(code, "code required");
        DatasetType datasetType = ALL_CODE_ENUM.get(code.toUpperCase());
        Objects.requireNonNull(code, "Error datasetType code");
        return datasetType;
    }
}
