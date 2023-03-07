package org.rhinodata.rhinobi.query.dsl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rhinodata.rhinobi.query.QueryException;
import org.rhinodata.rhinobi.query.common.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenye
 * @date 2023-02-03
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = SingleDql.class, name = "single"),
  @JsonSubTypes.Type(value = RatioDql.class, name = "ratio"),
  @JsonSubTypes.Type(value = DatasetSpec.class, name = "dataset"),
  @JsonSubTypes.Type(value = DimensionSpec.class, name = "dimension"),
  @JsonSubTypes.Type(value = MetricSpec.class, name = "metric"),
  @JsonSubTypes.Type(value = FilterSpec.class, name = "filter"),
  @JsonSubTypes.Type(value = OrderBySpec.class, name = "orderBy"),
  @JsonSubTypes.Type(value = LimitSpec.class, name = "limit")
})
public abstract class Dql implements Node {

  private final List<Dql> children = new ArrayList<>();

  public static Dql fromString(String dql) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(dql, Dql.class);
    } catch (Exception ex) {
      throw new QueryException(StrUtil.format("Dql 反序列化失败 ： {}", dql), ex);
    }
  }

  @Override
  public List<Dql> children() {
    return children;
  }

  public Dql addChild(Dql dql) {
    if (Objects.nonNull(dql)) {
      this.children().add(dql);
    }
    return this;
  }

  public static void main(String[] args) {
    String str = "{\n" +
            "  \"@type\": \"single\",\n" +
            "  \"dataset\": {\n" +
            "    \"@type\": \"dataset\",\n" +
            "    \"uuid\": \"f06ea418da38489194c70ec6e4875fa5\"\n" +
            "  },\n" +
            "  \"dimension\": {\n" +
            "    \"@type\": \"dimension\",\n" +
            "    \"items\": [\n" +
            "      {\n" +
            "        \"uuid\": \"17214dab11494072bbe2477a7fd882c0\",\n" +
            "        \"isDt\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"uuid\": \"97633ded309a4335885f2e0683db0bc6\",\n" +
            "        \"isDt\": false\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"metric\": {\n" +
            "    \"@type\": \"metric\",\n" +
            "    \"items\": [\n" +
            "      {\n" +
            "        \"uuid\": \"b042d59da7bb4111aa10958757cc7629\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"uuid\": \"96f866479dd0419b9eb47c5f422b3d35\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"filter\": {\n" +
            "    \"@type\": \"filter\",\n" +
            "    \"predicate\": {\n" +
            "      \"@type\": \"condition\",\n" +
            "      \"item\": {\n" +
            "        \"uuid\": \"b042d59da7bb4111aa10958757cc7629\"\n" +
            "      },\n" +
            "      \"opType\": \"=\",\n" +
            "      \"value\": \"上海\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"orderBy\": {\n" +
            "    \"@type\": \"orderBy\",\n" +
            "    \"items\": [\n" +
            "      {\n" +
            "        \"expression\": {\n" +
            "          \"uuid\": \"b042d59da7bb4111aa10958757cc7629\"\n" +
            "        },\n" +
            "        \"sortType\": \"desc\"\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n";
    Dql dql = Dql.fromString(str);
    System.out.println(dql.getClass().getName());
  }
}
