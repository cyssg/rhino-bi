package org.rhinodata.rhinobi.query.request;

import cn.hutool.core.lang.UUID;
import lombok.Data;
import org.rhinodata.rhinobi.common.api.BaseRequest;
import org.rhinodata.rhinobi.common.tool.constant.Constant;
import org.rhinodata.rhinobi.common.tool.utils.StringUtil;
import org.rhinodata.rhinobi.query.dsl.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author chenye
 * @date 2023-02-07
 */
@Data
public class QueryRequest extends BaseRequest {

  private String queryId;

  private String datasetUuid;

  private List<String> dimensionIdList;

  private List<String> metricIdList;

  public Query convert() {
    DatasetSpec datasetSpec = DatasetSpec.builder().datasetUuid(datasetUuid).build();
    List<Expression> dimExpressionList =
        dimensionIdList.stream().map(id -> new Expression(id)).collect(Collectors.toList());
    DimensionSpec dimensionSpec = new DimensionSpec(dimExpressionList);
    List<Expression> metricExpressionList =
        metricIdList.stream().map(id -> new Expression(id)).collect(Collectors.toList());
    MetricSpec metricSpec = new MetricSpec(metricExpressionList);
    SingleDql singleDql = new SingleDql(datasetSpec, dimensionSpec, metricSpec, null, null, null);
    if (StringUtil.isBlank(queryId)) {
      this.queryId = UUID.fastUUID().toString();
    }
    if (Objects.isNull(this.getRequestUser())) {
      this.setRequestUser(Constant.SYSTEM);
    }
    Query query = new Query(queryId, this.getRequestUser(), singleDql);
    return query;
  }
}
