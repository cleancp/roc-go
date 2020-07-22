package com.today.roc.go.dto.response.statistics;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
 * @createTime 2020年04月19日 15:05*
 * log.info()
 */
@ApiModel("违规率统计Vo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NegativeRateStatisticsVo implements Serializable {

    @ApiModelProperty("时间维度")
    private List<String> timeTerms;

    private List<NegativeRateStatisticsSubVo> negativeRateSubVos;
}
