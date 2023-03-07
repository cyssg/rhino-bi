package org.rhinodata.rhinobi.query.dsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.rhinodata.rhinobi.query.common.OpType;
import org.rhinodata.rhinobi.query.common.RelationalType;
import org.springframework.util.Assert;

import javax.annotation.concurrent.Immutable;
import java.util.List;

/**
 * @author chenye
 * @date 2023-02-03
 */
@Immutable
public class FilterSpec extends Dql {

  private final FilterPredicate predicate;

  @JsonCreator
  public FilterSpec(@JsonProperty("predicate") FilterPredicate predicate) {
    this.predicate = predicate;
  }

  @JsonProperty
  public FilterPredicate getPredicate() {
    return predicate;
  }

  @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
  @JsonSubTypes({
    @JsonSubTypes.Type(value = FilterCondition.class, name = "condition"),
    @JsonSubTypes.Type(value = FilterRelational.class, name = "relational")
  })
  public interface FilterPredicate {}

  @Immutable
  public static class FilterCondition implements FilterPredicate {
    private final ReferenceSpec item;
    private final OpType op;
    private final String value;

    @JsonCreator
    public FilterCondition(
        @JsonProperty(value = "item", required = true) ReferenceSpec item,
        @JsonProperty(value = "op", required = true) String op,
        @JsonProperty(value = "value", required = true) String value) {
      this.item = item;
      this.op = OpType.getByCode(op);
      this.value = value;
    }

    @JsonProperty
    public ReferenceSpec getItem() {
      return item;
    }

    @JsonProperty
    public OpType getOp() {
      return op;
    }

    @JsonProperty
    public String getValue() {
      return value;
    }
  }

  @Immutable
  public static class FilterRelational implements FilterPredicate {
    private final RelationalType rel;
    private final List<FilterPredicate> predicates;

    @JsonCreator
    public FilterRelational(
        @JsonProperty(value = "rel", required = true) String rel,
        @JsonProperty(value = "predicates", required = true) List<FilterPredicate> predicates) {
      Assert.notNull(predicates, "条件谓语标识符缺失");
      Assert.notNull(predicates, "条件谓词列表不得为空");
      Assert.isTrue(predicates.size() > 1, "条件谓词的个数必须大于等于2个");
      this.rel = RelationalType.getByCode(rel);
      this.predicates = predicates;
    }

    @JsonProperty
    public RelationalType getRel() {
      return rel;
    }

    @JsonProperty
    public List<FilterPredicate> getPredicates() {
      return predicates;
    }
  }
}
