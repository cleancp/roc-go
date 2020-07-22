package com.today.roc.go.dto.request.cost;

import com.today.roc.go.dto.response.cost.CostTimeStatisticsVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年06月15日 17:57*
 * log.info()
 */
@Data
public class ExportCostTimeStatisticsDto implements Serializable {

    private CostTimeStatisticsDto dto;

    @ApiModelProperty("第一个报表的选择项")
    private String name;

    @ApiModelProperty("导出样式 0：上面报表，1：下面报表")
    private String cell;

    private CostTimeStatisticsVo vo;

}
