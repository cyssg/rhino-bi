package org.rhinodata.rhinobi.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rhinodata.rhinobi.common.api.R;
import org.rhinodata.rhinobi.common.api.ResultCode;
import org.rhinodata.rhinobi.metadata.DatasetService;
import org.rhinodata.rhinobi.metadata.domain.Dataset;
import org.rhinodata.rhinobi.metadata.request.DatasetCreateRequest;
import org.rhinodata.rhinobi.web.controller.vo.DatasetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author chenye
 * @date 2023-01-31
 */
@RestController
@RequestMapping(value = "/dataset")
@Tag(name = "数据集")
public class DatasetController {

    private DatasetService datasetService;

    @Autowired
    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping(value = "/getByUuid")
    @Operation(summary = "根据UUID获得数据集")
    public R<DatasetVO> getByUuid(@RequestParam String uuid) {
        Dataset dataset = datasetService.getDataset(uuid);
        return R.data(DatasetVO.from(dataset));
    }

    @PostMapping(value = "/create")
    @Operation(summary = "创建数据集")
    public R<String> create(@RequestBody DatasetCreateRequest datasetCreateRequest) {
        datasetService.create(datasetCreateRequest);
        return R.success(ResultCode.SUCCESS);
    }

}
