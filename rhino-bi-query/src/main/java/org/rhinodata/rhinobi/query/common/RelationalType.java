package org.rhinodata.rhinobi.query.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenye
 * @date 2023-03-01
 */
@Getter
@AllArgsConstructor
public enum RelationalType {

  /** AND */
  AND("and", "and"),
  /** OR */
  OR("or", "or");

  private static final Map<String, RelationalType> ALL_CODE_ENUM =
      Stream.of(RelationalType.values())
          .collect(
              Collectors.toMap(p -> p.getCode().toUpperCase(Locale.ROOT), Function.identity()));

  /** 值 */
  final String code;
  /** 信息描述 */
  final String desc;

  public static RelationalType getByCode(String code) {
    Objects.requireNonNull(code, "code required");
    RelationalType relationalType = ALL_CODE_ENUM.get(code.toUpperCase(Locale.ROOT));
    Objects.requireNonNull(relationalType, "Error relationalType code");
    return relationalType;
  }
}
