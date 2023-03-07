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
 * @date 2023-03-02
 */
@Getter
@AllArgsConstructor
public enum SortType {

  /** ASC */
  ASC("asc", "正序"),
  /** DESC */
  DESC("desc", "倒序");

  private static final Map<String, SortType> ALL_CODE_ENUM =
      Stream.of(SortType.values())
          .collect(
              Collectors.toMap(p -> p.getCode().toUpperCase(Locale.ROOT), Function.identity()));

  /** 值 */
  final String code;
  /** 信息描述 */
  final String desc;

  public static SortType getByCode(String code) {
    Objects.requireNonNull(code, "code required");
    SortType sortType = ALL_CODE_ENUM.get(code.toUpperCase(Locale.ROOT));
    Objects.requireNonNull(sortType, "Error sortType code");
    return sortType;
  }
}
