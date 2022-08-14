package com.today.response.cost;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ^---^---^---^---^---^---^---^
 * --v---v---v---v---v---v---v--
 *
 * @author zou.cp
 * @version 1.0
 * @Description
 * @createTime 2020年06月09日 18:40*
 * log.info()
 */
@ApiModel("消耗时长统计Vo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTimeStatisticsVo implements Serializable {

    @ApiModelProperty("时间值/年")
    private Integer year;

    @ApiModelProperty("时间值/月")
    private Integer month;

    @ApiModelProperty("日/月")
    private List<String> timeTerms;

    @ApiModelProperty("消耗时长")
    private List<String> expendTimes;

    private List<CostTimeStatisticsSubVo> costTimeStatisticsSubVoList;

}
