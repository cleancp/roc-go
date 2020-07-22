package com.today.roc.go.dto.response.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
 * @createTime 2020年04月09日 14:04*
 * log.info()
 */
@ApiModel("质检统计Vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryQiStatisticsVo extends QueryCommonStatisticsVo implements Serializable {

    @ApiModelProperty("委案金额区间")
    private String commitMoneyScope;

    @ApiModelProperty("录音时长")
    private String recordTimeStr;

    @ApiModelProperty("违规量")
    private Long negativeCount;

    @ApiModelProperty("违规率")
    private String negativeRate;

    @ApiModelProperty(value = "违规率排序用",hidden = true)
    private Double sortNegativeRate;

}
