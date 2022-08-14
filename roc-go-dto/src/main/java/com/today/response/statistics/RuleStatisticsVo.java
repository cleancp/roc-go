package com.today.response.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年04月19日 15:05*
 * log.info()
 */
@ApiModel("反向录音质检规则统计Vo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleStatisticsVo implements Serializable {

    @ApiModelProperty("规则ID")
    private Long ruleId;

    @ApiModelProperty("规则名")
    private String ruleName;

    @ApiModelProperty("违规量")
    private Long negativeCount;

    @ApiModelProperty("违规率")
    private String negativeRate;

    @ApiModelProperty(value = "违规率排序用",hidden = true)
    private Double sortNegativeRate;

}
