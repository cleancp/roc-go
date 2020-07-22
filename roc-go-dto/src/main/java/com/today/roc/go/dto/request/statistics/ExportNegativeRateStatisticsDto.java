package com.today.roc.go.dto.request.statistics;

import com.today.roc.go.dto.response.statistics.NegativeRateStatisticsVo;
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
 * @createTime 2020年05月22日 14:35*
 * log.info()
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportNegativeRateStatisticsDto implements Serializable {

    @ApiModelProperty("导出类型 机构：commitOrgan 分公司：agentCompany")
    private String type;
    @ApiModelProperty("导出样式 0：违规率趋势，1：质检量统计")
    private String cell;
    @ApiModelProperty("机构/分公司名称-质检量导出使用")
    private String name;

    private NegativeRateStatisticsVo negativeRateStatisticsVo;

}
