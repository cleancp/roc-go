package com.today.response.statistics;

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
@ApiModel("违规率统计子Vo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NegativeRateStatisticsSubVo implements Serializable {

    @ApiModelProperty("机构、分公司……")
    private String groupFieldResult;

    @ApiModelProperty("违规量")
    private List<Long> negativeCountList;

    @ApiModelProperty("违规率")
    private List<String> negativeRateList;

    @ApiModelProperty("总质检量")
    private List<Long> qiCountList;
}
