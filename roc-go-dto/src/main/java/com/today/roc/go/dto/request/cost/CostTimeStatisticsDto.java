package com.today.roc.go.dto.request.cost;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年06月09日 18:31*
 * log.info()
 */
@ApiModel("商户消耗统计报表Dto")
@Data
public class CostTimeStatisticsDto implements Serializable {

    @ApiModelProperty("平台 管理端:admin 商户端:merchant")
    @NotBlank(message = "平台类型不可为空")
    private String platform;

    @ApiModelProperty("商户ID 管理端必传")
    private Long merchantId;

    @ApiModelProperty("时间类型  年:year  月:month")
    @NotBlank(message = "时间类型不可为空")
    private String timeType;

    @ApiModelProperty("时间值/年")
    @NotNull
    private Integer year;

    @ApiModelProperty("时间值/月")
    private Integer month;
}
