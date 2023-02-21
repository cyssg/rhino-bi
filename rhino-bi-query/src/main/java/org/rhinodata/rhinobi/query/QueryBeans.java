package org.rhinodata.rhinobi.query;

import org.rhinodata.rhinobi.dataset.DatasetService;
import org.springframework.stereotype.Service;

/**
 * @author chenye
 * @date 2023-02-06
 */
@Service
public record QueryBeans(DatasetService datasetService) {

}
