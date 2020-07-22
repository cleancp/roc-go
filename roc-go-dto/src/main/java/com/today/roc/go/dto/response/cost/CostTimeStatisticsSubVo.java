package com.today.roc.go.dto.response.cost;

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
 * @createTime 2020年06月09日 19:17*
 * log.info()
 */
@ApiModel("小时/日消耗SubVo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostTimeStatisticsSubVo implements Serializable {

    @ApiModelProperty("日/月")
    private String timeTerm;

    @ApiModelProperty("时间点:小时/日")
    private List<String> points;

    @ApiModelProperty("时间点消耗时长")
    private List<String> subSpendTimes;

}

