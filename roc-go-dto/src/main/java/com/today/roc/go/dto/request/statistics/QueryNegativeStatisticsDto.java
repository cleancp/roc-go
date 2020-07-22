package com.today.roc.go.dto.request.statistics;

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
 * @createTime 2020年04月09日 13:39*
 * log.info()
 */
@ApiModel("质检规则分析Dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryNegativeStatisticsDto extends CommonStatisticsDto implements Serializable {

    @ApiModelProperty("智能质检规则组")
    private List<Long> ruleIds;

    @ApiModelProperty(name = "sequence", value = "排序方式 DESC/ASC")
    private String sequence;

    @ApiModelProperty(name = "sortField", value = "排序字段 commitOrgan")
    private String sortField;
}
