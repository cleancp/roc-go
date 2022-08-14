package com.today.response.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年04月19日 16:29*
 * log.info()
 */
@ApiModel("违规率时间维度SubVo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NegativeRateSubVo {

    @ApiModelProperty("时间维度")
    private String date;

    @ApiModelProperty("违规量")
    private Long negativeCount;

    @ApiModelProperty("违规率")
    private String negativeRate;

    @ApiModelProperty("总质检量")
    private Long qiCount;

    @ApiModelProperty(value = "数值型-违规率 排序用")
    private Double sortNegativeRate;

}
