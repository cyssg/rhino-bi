package org.rhinodata.rhinobi.query.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author chenye
 * @date 2023-02-03
 */
@Data
public class QueryResult implements Serializable {

    private List<Map<String,Object>> result;
}
