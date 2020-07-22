package com.today.roc.go.dto.request.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年04月19日 15:08*
 * log.info()
 */
@ApiModel("违规率统计Dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NegativeRateDto {

//    @ApiModelProperty(value = "时间单位 日day 月month", hidden = true)
//    private String timeType;

    @ApiModelProperty("时间范围  近几天:7day、15day、30day ， 近几月:3month、6month、12month")
    private String timeScope;

    @ApiModelProperty("分组字段 (机构：commitOrgan 分公司：agentCompany)")
    private String groupField;

    @ApiModelProperty("下拉选择项")
    private List<String> initFieldList;

}
