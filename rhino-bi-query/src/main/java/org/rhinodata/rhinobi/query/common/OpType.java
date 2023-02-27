package org.rhinodata.rhinobi.query.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenye
 * @date 2023-02-25
 */
@Getter
@AllArgsConstructor
public enum OpType {

  /** EQ */
  EQ("=", "等于"),
  /** LT */
  LT("<", "小于");

  private static final Map<String, OpType> ALL_CODE_ENUM =
      Stream.of(OpType.values()).collect(Collectors.toMap(OpType::getCode, Function.identity()));

  /** 值 */
  final String code;
  /** 信息描述 */
  final String desc;

  public static OpType getByCode(String code) {
    Objects.requireNonNull(code, "code required");
    OpType opType = ALL_CODE_ENUM.get(code.toUpperCase());
    Objects.requireNonNull(code, "Error opType code");
    return opType;
  }
}
